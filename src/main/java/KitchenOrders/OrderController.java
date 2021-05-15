package KitchenOrders;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class OrderController {
	static Shelf hotShelf;
	static Shelf coldShelf;
	static Shelf frozenShelf;
	static Shelf overflowShelf;
	public static Map<String, Shelf> shelves = new HashMap<>();
	public static PriorityQueue<Shelf> availabilityOfShelves;
	
	public static Map<Integer, Set<Shelf>> numOfEmpty = new HashMap<>();
	public static Random rand;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, NumberFormatException, IOException {
		String JSON_ORDER_PATH = "./src/main/java/KitchenOrders/orders.json";
		String HOW_MANY = "howManyOrdersPerSecond";
        
		GetPropertyValues properties = new GetPropertyValues();

		// sorted by ascending order of  availability : hot < cold < frozen
		availabilityOfShelves = new PriorityQueue<>(
						(a, b) -> a.list.size()==b.list.size() ? 
						a.id - b.id : a.list.size()-b.list.size());
		
		initializeEachShelf(properties);
		
		shelves.put(hotShelf.temperature, hotShelf);
		shelves.put(coldShelf.temperature, coldShelf);
		shelves.put(frozenShelf.temperature, frozenShelf);
		shelves.put(overflowShelf.temperature, overflowShelf);
		rand = new Random();
		
		for (Shelf shelf: shelves.values()) {
			availabilityOfShelves.offer(shelf);
		}

        int numPerSec = Integer.valueOf(properties.getPropValues(HOW_MANY));
        
		receiveJSONOrder(JSON_ORDER_PATH, numPerSec);

	}
	
	private static void initializeEachShelf(GetPropertyValues properties) {
		int hotShelfCapacity = 10, coldShelfCapacity = 10, frozenShelfCapacity = 10, overflowShelfCapacity = 10;
		try {
			hotShelfCapacity = Integer.valueOf(properties.getPropValues("hotShelfCapacity"));
			coldShelfCapacity = Integer.valueOf(properties.getPropValues("coldShelfCapacity"));
			frozenShelfCapacity = Integer.valueOf(properties.getPropValues("frozenShelfCapacity"));
			overflowShelfCapacity = Integer.valueOf(properties.getPropValues("overflowShelfCapacity"));		
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hotShelf = new Shelf(1, "Hot Shelf", "hot", hotShelfCapacity);
		coldShelf = new Shelf(2, "Cold Shelf", "cold", coldShelfCapacity);
		frozenShelf = new Shelf(3, "Frozen Shelf", "frozen", frozenShelfCapacity);
		overflowShelf = new Shelf(4, "Overflow Shelf", "overflow", overflowShelfCapacity);		
	}
	
	private static void receiveJSONOrder (String jsonPath, int numPerSec) throws InterruptedException {
		
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader(jsonPath)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader); 
            JSONArray orderList = (JSONArray) obj;
                        
            int orderSize = orderList.toArray().length;
            //Iterate over order array
            for (int i = 0; i < orderSize; ) {
            	for (int j = 0; j < numPerSec && i < orderSize ; j++) {
            		Object orderObj = orderList.toArray()[i++];
    		    	Order order = parseOrderObject( (JSONObject) orderObj );

    		        System.out.println("ORDER: "+i+", "+ order.id +", " + order.name + ", " + order.temp + ", " + order.shelfLife + ", " + order.decayRate);
    		        
    		        Shelf shelf =  shelves.get(order.temp);
    		        // 1. Order =>   each shelf depending on hot, cold, frozen
    		        // 2. If the related shelf is not full, just put order on the shelf
    		        System.out.println(shelf.name + " :: " + shelf.getNumberOfOrders() + "::: " + shelf.capacity);
    		        if (shelf.getNumberOfOrders() < shelf.capacity) {
    		        	shelf.putOrder(order);
  		        	} else {
  		        	// 3. If the related shelf is full => try overflow shelf
  		        		
		        		// 3-1.  if the overflow shelf is not full, just put order on the overflow shelf
  		        		if (overflowShelf.getNumberOfOrders() < overflowShelf.capacity) {
    		        		overflowShelf.putOrder(order);
    		        	} else {
		        		// 3-2.  if the overflow shelf is full, choose an "existing order" from the 
    		        	// overflow shelf, and move it to an allowable shelf with room.  
    		        		boolean foundAndMove = false;
    		        		Order foundOrder = null;
    		        		// while file is not found yet and availabilityOfShelves is not looked thru
    		        		while (!foundAndMove && !availabilityOfShelves.isEmpty()) {
        		        		Shelf available = availabilityOfShelves.poll();
        		        		for (Order overflowOrder: overflowShelf.list) {
        		        			if (overflowOrder.temp.equals(available.temperature) && available.availableToPut()) {
        		        				foundOrder = overflowOrder;
        		        				// add "foundOrder" to the available shelf
          		        				available.list.add(foundOrder);
        		        				foundAndMove = true;
        		        				break;
        		        			}
        		        		}
    		        		}
	        				// remove "foundOrder" form the  overflowShelf 
    		        		if (foundAndMove) {
    		        			overflowShelf.list.remove(foundOrder);    		        			
    		        		} else {
    		        		//	4. If no "allowable shelf with room" is existing, 
    		        		// a random order from the "overflow" shelf should be discarded as waste. 
    		        			int randomIdx = rand.nextInt(overflowShelf.list.size());
    		        			overflowShelf.list.remove(randomIdx);
    		        			overflowShelf.putOrder(order);
    		        		}
    		        	}
    		        }
            	}
            	
		    	Thread.sleep(1000);    		    	
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }		
	}
	
	
	
	
    private static Order parseOrderObject(JSONObject orderObject) {
         
        //Get employee first name
        String id = (String) orderObject.get("id");    
         
        //Get employee last name
        String name = (String) orderObject.get("name");  
         
        //Get employee website name
        String temp = (String) orderObject.get("temp");    

        long shelfLife = (long) orderObject.get("shelfLife");    
        
        double decayRate = (double) orderObject.get("decayRate");    
//        System.out.print(id +", " + name + ", " + temp + ", " + shelfLife + ", " + decayRate);
//        System.out.println();        
        return new Order(id, name, temp, shelfLife, decayRate);
        
        
    }
    /*
    {
      "id": "a8cfcb76-7f24-4420-a5ba-d46dd77bdffd",
      "name": "Banana Split",
      "temp": "frozen",
      "shelfLife": 20,
      "decayRate": 0.63
    },
    {
      "id": "58e9b5fe-3fde-4a27-8e98-682e58a4a65d",
      "name": "McFlury",
      "temp": "frozen",
      "shelfLife": 375,
      "decayRate": 0.4
    },
    {
      "id": "2ec069e3-576f-48eb-869f-74a540ef840c",
      "name": "Acai Bowl",
      "temp": "cold",
      "shelfLife": 249,
      "decayRate": 0.3
    },
   */
}

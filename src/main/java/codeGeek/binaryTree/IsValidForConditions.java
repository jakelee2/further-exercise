package codeGeek.binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
[id, code, parent_id]
[
	[2, null, null],
	[0, null, 2],
	[1, null, 2],
	[3, null, 2],
	[7, 100, 0],
	[4, 200, 1],
	[8, 300, 3],
	[5, 200, 3],
	[6, 400, 4],
	....
]

          2
     /    |    \
    0     1     3
   /      |    / \
  7       4   8   5
          |
          6

----------------------

          2
      /       \
     0         3
    / \       / \
   7   4     8   5
 [100][500][300][200]

 1. Only leaf node should have code
 2. Tree should be square
 3. Code should be unique

*/

public class IsValidForConditions {

	public static void main(String[] args){
		
        List<STNode> nodes = new ArrayList<STNode>();
        nodes.add(new STNode(2,null,null));
        nodes.add(new STNode(0, null, 2));
        nodes.add(new STNode(1, null, 2));
        nodes.add(new STNode(3, null, 2));
        nodes.add(new STNode(7, 100, 0));
        nodes.add(new STNode(4, 200, 1));
        nodes.add(new STNode(8, 300, 3));
        nodes.add(new STNode(5, 200, 3));
        nodes.add(new STNode(6, 400, 4));
        
//        isValidForConditions(nodes);
        
        Integer[][] nodesArray = 
    	{
			{2, null, null},
			{0, null, 2},
			{3, null, 2},
			{7, 100, 0},
			{4, 200, 0},
			{8, 300, 3},
			{5, 200, 3}
    	};

        isValidForConditions(nodesArray);
	}
	
	public static boolean isValidForConditions(Integer[][] nodes){
		

		//	Tree should be square
		// 
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < nodes.length; i++){
			if(map.get(nodes[i][2]) == null){
				map.put(nodes[i][2], 1);
			}
			else{
				Integer newVal = map.get(nodes[i][2]) + 1;
				if(newVal > 2)
					return false;
				else
					map.put(nodes[i][2], map.get(nodes[i][2])+1);
			}
						
		}

		List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
		for(Map.Entry<Integer, Integer> ent: list){
			
		}
		

//		Only leaf node should have code
//		Code should be unique

		return true;
	}
	
}

class STNode {
	public Integer id, parentId, weight;

	public STNode(Integer id, Integer parentId, Integer weight) {
		this.id = id;
		this.parentId = parentId;
		this.weight = weight;
	}
}

package codeGeek.linkedList;

public class ReverseLinkedList {

	public static ListNode reverseRecursive(ListNode node)
	{
		if(node == null || node.next == null)
			return node;
		
		ListNode HeadNode = reverseRecursive(node.next);
		node.next.next = node;
		node.next = null;
		
		return HeadNode;
	}
	
	
	public static ListNode reverseIter(ListNode node)
	{
		ListNode pTemp1 = null, pTemp2 = null;
		ListNode pCurrent = node;
		
		while(pCurrent != null){
			pTemp1 = pCurrent.next;
			pCurrent.next = pTemp2;
			pTemp2 = pCurrent;
			pCurrent = pTemp1;
			
		}
		
		return pTemp2;
		
	}
	
}

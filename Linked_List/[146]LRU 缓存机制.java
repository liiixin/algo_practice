//运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。 
//
// 
// 
// 实现 LRUCache 类： 
//
// 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上
//限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。 
// 
//
// 
// 
// 
//
// 进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？ 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 3000 
// 0 <= value <= 104 
// 最多调用 3 * 104 次 get 和 put 
// 
// Related Topics 设计 哈希表 链表 双向链表 
// 👍 1464 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
import java.util.HashMap;

class LRUCache {
    // 看上面的 LRUCache.java
    HashMap<Integer, Node> map;
    DoubleLinkedList cache;
    int cap;

    public LRUCache(int capacity) {
        map = new HashMap<>();
        cache = new DoubleLinkedList();
        cap = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        int val = map.get(key).val;
        put(key, val);

        return val;
    }
    
    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        if (map.containsKey(key)){
            cache.delete(map.get(key));
        }else {
            if (map.size() == cap){
                int k = cache.deleteLast();
                map.remove(k);
            }
        }
        cache.addFirst(newNode);
        map.put(key, newNode);
    }
}
class DoubleLinkedList{
    Node head;
    Node tail;

    public DoubleLinkedList(){
        // 构造时，就创建了head和tail这两个假节点
        head = new Node(0,0);
        tail = new Node(0,0);

        head.next = tail;
        tail.prev = head;
    }

    public void addFirst(Node node){

        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    public int delete(Node node){
        int key = node.key;
        node.next.prev = node.prev;
        node.prev.next = node.next;

        return key;
    }

    public int deleteLast(){
        if (head.next == tail){
            return -1;
        }

        return delete(tail.prev);
    }
}
class Node{
    public int key;
    public int val;
    public Node next;
    public Node prev;

    public Node(int key, int val){
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

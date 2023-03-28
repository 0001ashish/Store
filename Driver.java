import java.util.*;

public class Driver{
    public static void main(String[] args) {
        Storage drive = new Storage();
        
        // for(int i = 0;i<drive.storage.length;i++){
        //     for(int j = 0;j<drive.storage[0].length;j++){
        //         System.out.print(drive.storage[i][j]+" ");
        //     }
        // }

        BufferCache cache = new BufferCache();
        BufferCache.FreeBuffer free_head = cache.getHead();
        // BufferCache.FreeBuffer free_iterator;

        // for(free_iterator=cache.getHead();free_iterator.next!=free_head;free_iterator = free_iterator.next){
        //     System.out.print(free_iterator.blk_no+" ");
        // }

        /* Generating random block number to be read from the memory & 
        applying buffer cache algorithm */
        Random rand = new Random(); //Used for random number generation
        BufferCache.HashQueue[] queue = cache.getInitial();
        for(int i=0;i<10;i++){
            int blk_no = rand.nextInt(100);
            int data = drive.getBlock(blk_no);
            queue[blk_no%10].addtoHash(data); // add data to appropriate hashqueue
            System.out.println(blk_no+" "+data);
            
        }
        System.out.println(" ");

        for(int i=0;i<10;i++){
            BufferCache.FreeBuffer temp = queue[i].next;
            if(temp==null){
                continue;
            }
            System.out.print(i+"-->");
            System.out.print(temp.data+" ");
            while(temp.next!=queue[i].next){
                temp = temp.next;
                System.out.print(temp.data+" ");
            }
            System.out.println(" ");
            
        }
    }
}
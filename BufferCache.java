public class BufferCache{
    private FreeBuffer head;
    private HashQueue initial[];

    public class FreeBuffer{
        FreeBuffer next;
        FreeBuffer prev;
        boolean delayed_wr;
        boolean available;
        public int blk_no;
        public int data;

        FreeBuffer(){
            delayed_wr = false;
            available = true;
            blk_no = -1;
            data =-1;
            next = null;
            prev = null;
        }

        public FreeBuffer getNext(){
            return next;
        }

        public void setNext(FreeBuffer next){
            this.next = next;
        }

        public FreeBuffer getPrev(){
            return prev;
        }

        public void setPrev(FreeBuffer prev){
            this.prev = prev;
        }

        public boolean isDelayed_wr(){
            return delayed_wr;
        }

        public void setDelayed_wr(boolean delayed_wr){
            this.delayed_wr = delayed_wr;
        }

        public void addBuffer(FreeBuffer ref){
            if(this.next == null){
                this.next = ref;
                ref.setPrev(this);
            }else{
                this.next.setPrev(ref);
                ref.setNext(this.next);
                this.next = ref;
                ref.setPrev(this);
            }
        }

    }

    public class HashQueue{
        FreeBuffer next;

        HashQueue(){
            next = null;
        }

        public void setNext(FreeBuffer next){
            this.next = next;
        }

        // public void setPrev(FreeBuffer prev){
        //     this.prev = prev;
        // }

        public FreeBuffer getNext(){
            return next;
        }

        // public FreeBuffer getPrev(){
        //     return prev;
        // }
        ////Complete in the morning...
        private FreeBuffer find(){
            FreeBuffer temp = this.next;
            while(temp.next!=this.next){
                temp = temp.next;
                if(temp == null){
                    return null;
                }
            }
            return temp;
        }

        private int addLink(HashQueue ref){
            FreeBuffer temp = head.next;
            if(head.next==null){
                return -1;
            }
            
            while(temp.delayed_wr){
                temp = temp.next;
                if(temp.next==head.next){
                    return -2;
                }
            }
            if(ref.next==null){
                ref.setNext(temp);//Left incomplete here at exactly 9:58 AM on 28th of March, 2023
                
            }
            ref.setNext(temp);
            return 1;
        }
        public boolean addtoHash(int data){
            FreeBuffer temp = this.find();
            if(temp != null){
                return false;
            }
            if(this.next == null){
                this.next = new FreeBuffer();
                this.next.data = data;
                this.next.setPrev(this.next);
                this.next.setNext(this.next);
            }else{
                FreeBuffer first = new FreeBuffer();
                first.data = data;
                first.next = this.next;
                first.prev = this.next.prev;
                this.next.prev.setNext(first);
                this.next.prev = first;
                this.next = first;
            }
        }

    }

    public BufferCache(){
        /*Initializing FreeBuffer */
        head = new FreeBuffer();
        FreeBuffer iterator = head;
        FreeBuffer temp=null;
        for(int i = 0; i < 25; i++){
            temp = new FreeBuffer();
            iterator.setNext(temp);
            temp.setPrev(iterator);
            iterator.blk_no = i;
            iterator = iterator.next;
        }

        temp.setNext(head);
        head.setPrev(temp);
        FreeBuffer temp2 = new FreeBuffer();
        temp2.blk_no = 100;
        head.addBuffer(temp2);

        /*Initializing HashQueue */
        initial = new HashQueue[10];
        for(int i = 0; i < 10; i++){
            initial[i] = new HashQueue();
        }

    }
                
    public FreeBuffer getHead(){
        return head;
    }

    public HashQueue[] getInitial() {
        return initial;
    }
}
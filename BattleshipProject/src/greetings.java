public class greetings {
        private String msgName;

        //Getter
        public String getMsgName()
        {
            return msgName;
        }

        //Setter
        public void setMsgName(String newMsgName)
        {
            this.msgName = newMsgName;
        }
    }
    //inheritance / polymorphism
    class userAction extends greetings {
        public void userMsg() {
            System.out.println("\nDeploy your ships:");
        }
    }
    //inheritance/ polymorphism
    class announcement extends greetings{
    public void gameOver(){
        System.out.println("\n*** GAME OVER ***");}
    }


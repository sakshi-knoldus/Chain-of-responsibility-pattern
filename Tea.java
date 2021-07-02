// Chain of Responsibility design pattern. 

public class Tea {
    
    private boolean milk = false;
    private boolean suger = false;
    
    public void setMilk(boolean b) {
        this.milk = b;
    }
    
    public void setSuger(boolean b) {
        this.suger = b;
    }
    
    public void drink() {
        System.out.print("Drinking tea with ");
        if (milk) {
            System.out.print("milk and ");
        } else {
            System.out.print("no milk and ");
        }
        if (suger) {
            System.out.println("suger.");
        } else {
            System.out.println("no suger.");
        }
    }
}

public interface Chain {
    
    public void setNextChain(Chain nextChain);
    
    public void nextInstruction(Tea tea);
}

public class AddMilk implements Chain {
    
    private Chain chain;
    
    @Override
    public void setNextChain(Chain chain) {
        this.chain = chain;
    }
    
    @Override
    public void nextInstruction(Tea tea) {
        System.out.println("Adding milk");
        tea.setMilk(true);
        this.chain.nextInstruction(tea);
    }
    
}

public class AddSuger implements Chain {
    
    private Chain chain;
    
    @Override
    public void setNextChain(Chain chain) {
        this.chain = chain;
    }
    
    @Override
    public void nextInstruction(Tea tea) {
        System.out.println("Adding suger");
        tea.setSuger(true);
        this.chain.nextInstruction(tea);
    }
}

public class DrinkTea implements Chain {
    
    private Chain chain;
    
    @Override
    public void setNextChain(Chain chain) {
        this.chain = chain;
    }
    
    @Override
    public void nextInstruction(Tea tea) {
        tea.drink();
    }
}

public class Client {
    
        private Chain chainOne;
    
    public Client() {
        
        this.chainOne = new AddMilk();
        Chain chainTwo = new AddSuger();
        Chain chainThree = new DrinkTea();
        
        chainOne.setNextChain(chainTwo);
        chainTwo.setNextChain(chainThree);
        
    }
    
    public void startChain(Tea tea) {
        this.chainOne.nextInstruction(tea);
    }
    
}


public class Main
{
    public static void main(String[] args) {
        
        Client client = new Client();
        
        client.startChain(new Tea());
    }
}

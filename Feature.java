import java.util.ArrayList;
import java.util.Scanner;

public class Ex{

    private ArrayList<Pokemon>  pList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    // Giá trị mạc định cho pList
    public void inputAvailable() {
        pList.add(new Pokemon("Chu de dan", "Water", 200, 50));
        pList.add(new Pokemon("Cam Suc", "Fire", 170, 70));
        pList.add(new Pokemon("Cam Lan", "Grass", 230, 40));
    }

    // Thêm 1 Pokemon vào List
    public void addNewPokemon() {
        String Name, Type;
        int Hp, Dame;
        System.out.println("Input the Name of Pokemon, the Name must not exsit yet: ");
        Name = sc.nextLine();
        if(pList != null) {
            for(Pokemon pkm : pList)
            {
                if(pkm.getName().equalsIgnoreCase(Name)) {
                    System.out.println("The Name already exist!, please input the another name: ");
                    Name = sc.nextLine();
                    do {
                        System.out.println("The Name already exist!, please input the another name: ");
                        Name = sc.nextLine();
                    }
                    while(pkm.getName().equalsIgnoreCase(Name));
                    break;
                }
            }
        }
       
        System.out.println("Input the Type of the Pokemon (Fire, Water, Grass, Soil): ");
        Type = sc.nextLine();

        System.out.println("Input the Dame of the Pokemon: ");
        Dame = sc.nextInt();

        System.out.println("Input the Hp of the Pokemon: ");
        Hp = sc.nextInt();

        pList.add(new Pokemon(Name, Type, Hp, Dame));
    }

    // Thêm nhiều Pokemon vào List
    public void InputListPokemon() {
        int size;
        System.out.println("Input the quantity of Pokemon: ");
        
        size = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < size; i++) {
            addNewPokemon();
            sc.nextLine();
        }
    }

    // Xuất dnah sách Pokemon
    public void OutputListPokemon() {
        int stt = 0;
        System.out.println("\n+=====+=========================+===============+==========+===============+");
        System.out.format("|%-5s|%-25s|%-10s|%-15s|%-15s|", "STT", "Name", "Type", "Hp", "Dame");
        System.out.println("\n+=====+=========================+===============+==========+===============+");
        
        for(Pokemon p : pList)
        {   
            stt++;
            p.setStt(stt);
            p.ShowInfo();
        }
        System.out.println("+=====+=========================+===============+==========+===============+");
    }
    
    // Chọn ra 2 pokemon thi đấu
    public Pokemon choicePokemon() {
        int choice;
        if(pList != null) {
            System.out.println("Select Pokemon base on STT: ");
            choice = sc.nextInt();
            for (Pokemon p : pList) {
                if(p.getStt() == choice) {
                    System.out.println("The Pokemon you selected is ");

                    System.out.println("\n+=====+=========================+===============+==========+===============+");
                    System.out.format("|%-5s|%-25s|%-10s|%-15s|%-15s|", "STT", "Name", "Type", "Hp", "Dame");
                    System.out.println("\n+=====+=========================+===============+==========+===============+");
                    p.ShowInfo();
                    System.out.println("+=====+=========================+===============+==========+===============+");
                    return p;
                }
            }
            System.out.println("The Pokemon you selected is not exist!!");
        }
        else {
            System.out.println("List of Pokemon is empty!!");
            
        }
        return null;
    }

    // Show the current hp
    public void showCurrentHp(Pokemon pkm1, Pokemon pkm2) {
        System.out.println("+=====+=========================+===============+==========+===============+");
        System.out.format("|%-5s|%-25s|%-10s|%-15s|%-15s|", "STT", "Name", "Type", "Hp", "Dame");
        pkm1.ShowInfo();
        pkm2.ShowInfo();
        System.out.println("+=====+=========================+===============+==========+===============+");
    }
    // Chuẩn bị cho Battle
    public void beginBattle() {
        int round = 2;

        System.out.println("\n-------------------Battle Ground: Begin--------------------\n");
        // chọn ra 2 pokemon
        OutputListPokemon();
        Pokemon pkm1 = choicePokemon();
        Pokemon pkm2 = choicePokemon();

        // Kiểm tra khắc hệ
        pkm1.setBenefit_Type(pkm2);

        //Yếu hơn được đánh trước
        System.out.println("\n-----------Begin Round 1!!-----------");
        if(pkm1.StrongerPokemon(pkm2)) {
            System.out.println("Because Pokemon " + pkm1.getName() + " stronger than " + pkm2.getName() + "so " + pkm2.getName() + " has first attack!!\n");
            pkm2.Attack(pkm1);
            pkm2.setTurn(true);
        }
        else {
            System.out.println("Because Pokemon " + pkm2.getName() + " stronger than " + pkm1.getName() + "so " + pkm1.getName() + "has first attack!!");
            pkm1.Attack(pkm2);
            pkm1.setTurn(false);
        }

        do {
            System.out.println("\n-----------Begin Round " + round + "!!-----------");
            if(pkm1.getTurn())
            {
                pkm1.Attack(pkm2);
            }
            else {
                pkm2.Attack(pkm1);
            }
        // System.out.println("==============================Current Infomation============================");
        //     showCurrentHp(pkm1, pkm2);
            if(pkm1.getHp() <= 0) {
                System.out.println("The Pokemon " + pkm1.getName() + " lost!!");
            }
            if(pkm2.getHp() <= 0) {
                System.out.println("The Pokemon " + pkm2.getName() + " lost!!");
            }
            round++;
        }
        while(pkm1.getHp() > 0 && pkm2.getHp() > 0);
    }
}


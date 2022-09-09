import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

/**
 * Pokemon
 */
public class Pokemon {

    private String Name;
    private String Type;
    private int Hp;
    private int Dame;
    private boolean Buff_Type = false; // false không được buff từ khắc hệ
    private boolean Stronger = false;   // kiểm tra Pokemon mạnh hơn
    //Số thứ tự của PKM trong list
    private int Stt;
    private boolean Turn = true;   //false là chưa tới lượt đánh

    public Pokemon(String Name, String Type, int Hp, int Dame) {
        this.Name = Name;
        this.Type = Type;
        this.Hp = Hp;
        this.Dame = Dame;
    }

    public void setDame(int dame) {
        Dame = dame;
    }
    public int getDame() {
        return Dame;
    }

    public void setHp(int hp) {
        Hp = hp;
    }
    public int getHp() {
        return Hp;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getName() {
        return Name;
    }

    public void setStt(int stt) {
        Stt = stt;
    }
    public int getStt() {
        return Stt;
    }

    public void setType(String type) {

    }
    public String getType() {
        return Type;
    }

    public void setTurn(boolean Turn) {
        this.Turn = Turn;
    }
    public boolean getTurn() {
        return Turn;
    }

    public void ShowInfo()
    {
        System.out.format("|%-5s|%-25s|%-10s|%-15s|%-15s|\n",Stt, Name, Type, Hp, Dame);
    }

    // Pokemon attack
    public void Attack(Pokemon pkm) {
        pkm.Hp -= this.Dame;
        System.out.println(pkm.Name + " lost " + this.Dame + " Hp!!, The current Hp is " + pkm.Hp + "\n");
        this.Turn = false;
        pkm.Turn = true;
    }

    // Check Type Pokemon
    public void setBenefit_Type(Pokemon pkm) {
        if(this.Type == "Water") {
            if(pkm.Type == "Fire") {
                this.Dame += this.Dame * (3/10);
                this.Buff_Type = true;
            }
            if(pkm.Type == "Soil") {
                pkm.Buff_Type = true;
                pkm.Dame += pkm.Dame * (25/100);
            }
            if(pkm.Type == "Grass") {
                pkm.Buff_Type = true;
                pkm.Dame += pkm.Dame * (15/100);
            }
        }

        if(this.Type == "Fire") {
            if(pkm.Type == "Graas") {
                this.Buff_Type = true;
                this.Dame += this.Dame * (2/10);
            }
            if(pkm.Type == "Soil") {
                pkm.Buff_Type = true;
                pkm.Dame += pkm.Dame * (35/100);
            }
        }

        if(this.Type == "Soil") {
            if(pkm.Type == "Grass") {
                pkm.Buff_Type = true;
                pkm.Dame += pkm.Dame * (1/10);
            }
        }
    }

    // Set stronger pokemon
    public boolean StrongerPokemon(Pokemon pkm) 
    {
        if( 
            (this.Dame > pkm.Dame && (this.Hp > pkm.Hp || this.Buff_Type == true))
            || ((this.Buff_Type == true) && (this.Hp > pkm.Hp || this.Dame > pkm.Dame))
        )  {
                return true;
        }     
        return false;       
    }

}

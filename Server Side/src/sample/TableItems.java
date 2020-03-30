package sample;

import javafx.scene.image.ImageView;

public class TableItems {
    private String Name;
    private String Classs;
    private String Sec;
    private ImageView image;



	public TableItems(String Name, String Class, String Sec,ImageView Image){
        this.Name=Name;
        this.Classs=Class;
        this.Sec=Sec;
        this.image = Image;

    }
	

    public ImageView getImage() {
		return image;
	}


	public void setImage(ImageView image) {
		this.image = image;
	}



    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;

    }

    public String getSec() {
        return Sec;
    }

    public void setSec(String sec) {
        Sec = sec;
    }


    public String getClasss() {
        return Classs;
    }

    public void setClasss(String classs) {
        Classs = classs;
    }

}

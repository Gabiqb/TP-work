package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class BaseProduct implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int protein;
    private int fat;
    private int carbs;
    private int price;
    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtein() {
        return protein;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public BaseProduct(String title, double rating, int calories, int protein, int fat, int carbs,int price)
    {
        this.title=title;
        this.rating=rating;
        this.calories=calories;
        this.protein=protein;
        this.fat=fat;
        this.carbs=carbs;
        this.price=price;
    }

    public int getPrice() {
        return price;
    }

    public boolean equals(Object o)
    {
        BaseProduct b=(BaseProduct) o;
        if(!b.getTitle().equals(this.getTitle()))
            return false;
        if(b.getCalories()!=this.getCalories())
            return false;
        if(b.getRating()!=this.getRating())
            return false;
        if(b.getProtein()!=this.getProtein())
            return false;
        if(b.getFat()!=this.getFat())
            return false;
        if(b.getCarbs()!=this.getCarbs())
            return false;
        if(b.getPrice()!=this.getPrice())
            return false;
        return true;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        return prime * 3 + price;
    }
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
    {

        title=aInputStream.readUTF();
        rating=aInputStream.readDouble();
        calories=aInputStream.readInt();
        protein=aInputStream.readInt();
        fat=aInputStream.readInt();
        carbs=aInputStream.readInt();
        price=aInputStream.readInt();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException
    {
        aOutputStream.writeUTF(title);
        aOutputStream.writeDouble(rating);
        aOutputStream.writeInt(calories);
        aOutputStream.writeInt(protein);
        aOutputStream.writeInt(fat);
        aOutputStream.writeInt(carbs);
        aOutputStream.writeInt(price);

    }

}

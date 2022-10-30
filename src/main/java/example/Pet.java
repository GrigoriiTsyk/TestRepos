package example;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Pet {
    private StringProperty kind;
    private StringProperty Name;
    private StringProperty OwnerName;
    private IntegerProperty month, year;

    public StringProperty kindStringProperty(){
        if(kind == null)
            kind = new SimpleStringProperty();

        return kind;
    }

    public final void setKind(String value){ kindStringProperty().set(value);}

    public final String getKind(){ return kindStringProperty().get();}

    public StringProperty nameStringProperty(){
        if(Name == null)
            Name = new SimpleStringProperty();

        return Name;
    }

    public final void setName(String value){ nameStringProperty().set(value); }

    public final String getName(){ return nameStringProperty().get();}

    public StringProperty OwnerNameStringProperty(){
        if(OwnerName == null)
            OwnerName = new SimpleStringProperty();

        return OwnerName;
    }

    public final void setOwnerName(String value){ OwnerNameStringProperty().set(value);}

    public final String getOwnerName(){ return OwnerNameStringProperty().get();}

    public IntegerProperty monthIntegerProperty(){
        if(month == null)
            month = new SimpleIntegerProperty();

        return month;
    }

    public final void setMonth(Integer value){monthIntegerProperty().set(value);}

    public final Integer getMonth(){return monthIntegerProperty().get();}

    public IntegerProperty yearIntegerProperty(){
        if(year == null)
            year = new SimpleIntegerProperty();

        return year;
    }

    public final void setYear(Integer value){yearIntegerProperty().set(value);}

    public final Integer getYear(){return yearIntegerProperty().get();}

    public Pet(String kind, String name, int year, int month, String ownerName){
        setKind(kind);
        setName(name);
        setMonth(month);
        setYear(year);
        setOwnerName(ownerName);
    }
}

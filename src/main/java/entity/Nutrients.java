package entity;

public class Nutrients {

    private int amount;

    private String unit;
    private int percentOfDailyNeeds;
    private String title;

    public Nutrients(String title, int amount, String unit, int percentOfDailyNeeds) {
        this.title = title;
        this.amount = amount;
        this.unit = unit;
        this.percentOfDailyNeeds = percentOfDailyNeeds;
        
    }
}

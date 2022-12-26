public class MyDate {
    private int years = 1998;
    private int months = 12;
    private int days = 4;

    public MyDate() {
    }

    public MyDate(int years, int months, int days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setMonths(int months) {
        if (months <= 0 || months > 12) {
            return;
        }
        this.months = months;
    }

    public void setDays(int days) {
        if (days > 31 || days <= 0) {
            return;
        }
        this.days = days;
    }

    public int getYears() {
        return years;
    }

    public int getMonths() {
        return months;
    }

    public int getDays() {
        return days;
    }


    public String say() {
        String s = years + "年" + (months < 10 ? "0" : "") + months + "月" + (days < 10 ? "0" : "") + days + "日";
        return s;
    }

}

package contacts;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Record implements Serializable {
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String number;
    private LocalDateTime timeCreated;
    private LocalDateTime lastEdited;

    public void setTimeCreated() {
        this.timeCreated = LocalDateTime.now();
    }
    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setLastEdited() {
        this.lastEdited = LocalDateTime.now();
    }
    public LocalDateTime getLastEdited() {
        return lastEdited;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        if (isValidNumber(number)) {
            this.number = number;
            return;
        }
        this.number = "";
    }

    private static boolean isValidNumber(String number) {
        if (number.matches("\\+?(\\w+(([ \\-]{1}\\w{2,})*)|\\(\\w+\\)(([ \\-]{1}\\w{2,})*)|\\w+([ \\-]{1}\\(\\w{2,}\\))(([ \\-]{1}\\w{2,})*))")) {
            return true;
        }
        System.out.println("Wrong number format!");
        return false;
    }

    public boolean hasNumber() {
        return !getNumber().isEmpty();
    }

    abstract String info();

    abstract String fullName();

    abstract void setField(String fieldName, String fieldValue);

    abstract String[] getEditableFields();
}
package contacts;

import java.time.LocalDate;

class Person extends Record {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        if (birthDate.trim().isEmpty()) {
            alert("Bad birth date!");
            return;
        }
        this.birthDate = LocalDate.parse(birthDate);
    }
    private boolean hasBirthDate() {
        return getBirthDate() != null ? true : false;
    }

    public String getGender() {
        return (gender == null || gender.isEmpty())? "[no data]" : gender;
    }
    public void setGender(String gender) {
        if (gender.trim().isEmpty()) {
            alert("Bad gender!");
            this.gender = "";
        } else {
            this.gender = gender;
        }
    }

    private void alert(String message) {
        System.out.println(message);
    }

    @Override
    public String[] getEditableFields() {
        String[] editableFields = {"name", "surname", "birth", "gender", "number"};
        return editableFields;
    }

    @Override
    public void setField(String fieldName, String fieldValue) {
        switch (fieldName) {
            case "name":
                setFirstName(fieldValue);
                break;
            case "surname":
                setLastName(fieldValue);
                break;
            case "birthDate":
                setBirthDate(fieldValue);
                break;
            case "gender":
                setGender(fieldValue);
                break;
            case "number":
                setNumber(fieldValue);
        }
    }

    @Override
    public String fullName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public String info() {
        return "Name: " + firstName + "\n"
                + "Surname: " + lastName +  "\n"
                + "Birth date: " + (hasBirthDate() ? birthDate : "[no data]") + "\n"
                + "Gender: " + getGender() + "\n"
                + "Number: " + (hasNumber() ? getNumber() : "[no number]") + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdited();
    }
}
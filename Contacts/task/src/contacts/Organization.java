package contacts;

class Organization extends Record {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String[] getEditableFields() {
        String[] editableFields = {"name", "address", "number"};
        return editableFields;
    }

    @Override
    public void setField(String fieldName, String fieldValue) {
        switch (fieldName) {
            case "name":
                setName(fieldValue);
                break;
            case "address":
                setAddress(fieldValue);
                break;
            case "number":
                setNumber(fieldValue);
        }
    }

    @Override
    public String fullName() {
        return getName();
    }

    @Override
    public String info() {
        return "Organization name: " + getName() + "\n"
                + "Address: " + getAddress() + "\n"
                + "Number: " + (hasNumber() ? getNumber() : "[no number]") + "\n"
                + "Time created: " + getTimeCreated() + "\n"
                + "Time last edit: " + getLastEdited();
    }
}
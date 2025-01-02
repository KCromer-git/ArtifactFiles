public class Contact{
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address){
        if(contactId == null || contactId.length() > 10){
            throw new IllegalArgumentException("Invalid contact ID");
        }

        if(firstName == null || firstName.length() > 10){
            throw new IllegalArgumentException("Invalid first name");
        }

        if(lastName == null || lastName.length() > 10){
            throw new IllegalArgumentException("Invalid last name");
        }

        if(phone == null || phone.length() != 10 ||)!phone.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid phone number");
        }

        if(address == null || address.length() > 30){
            throw new IllegalArgumentException("Invalid address");
        }

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }


}

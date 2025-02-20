package Entities;

public class UserFactory {

    private Person person;

    public UserFactory()
    {
        person = null;
    }

    public Person createUserType(int i)
    {
        switch (i)
        {
            case 1:
                return new Agent();
            case 2:
                return new Buyer();
            case 3:
                return new Seller();
            default:
        }

        return null;
    }
}

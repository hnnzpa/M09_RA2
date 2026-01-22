public class Associacio {
    private int numSocis = 1000;
    Soci[] socis = new Soci[numSocis];

    public Associacio() {
        for (int i = 0; i < socis.length; i++) {
            socis[i] = new Soci();
        }
    }

}

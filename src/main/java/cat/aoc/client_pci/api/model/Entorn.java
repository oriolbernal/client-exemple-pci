package cat.aoc.client_pci.api.model;

public enum Entorn {
    DEV,
    PRE,
    PRO;

    public String getEndpoint(Cluster cluster) {
        String clusterName = cluster.name().toLowerCase();
        return "https://" + getHost() + "." + clusterName + ".aoc.cat:443";
    }

    private String getHost() {
        switch (this) {
            case DEV:
                return "serveis3-dev";
            case PRE:
                return "serveis3-pre";
            case PRO:
                return "serveis3";
            default:
                throw new IllegalStateException("Entorn no definit: " + this);
        }
    }

}

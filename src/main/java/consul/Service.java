package consul;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service {
    private List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
    private Consul consul;

    public class ServiceProvider {
        String id;
        String address;
        String node;
        String name;
        Integer port;
        String[] tags;

        ServiceProvider(String name, String[] tags) {
            this.name = name;
            this.tags = tags;
        }

        ServiceProvider(JSONObject obj) {
            id = obj.optString("ServiceID");
            address = obj.optString("Address");
            node = obj.optString("Node");
            name = obj.optString("ServiceName");
            port = obj.optInt("ServicePort");
            // tags = obj.optJSONArray("ServiceTags");
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getNode() {
            return node;
        }

        public void setNode(String node) {
            this.node = node;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String[] getTags() {
            return tags;
        }

        public void setTags(String[] tags) {
            this.tags = tags;
        }

        @Override
        public String toString() {
            return "ServiceProvider [id=" + id + ", address=" + address + ", node=" + node + ", name=" + name + ", port=" + port +
                            ", tags=" + Arrays.toString(tags) + "]";
        }
    }

    Service(Consul consul) {
        this.consul = consul;
    }

    void add(JSONObject obj) {
        providers.add(new ServiceProvider(obj));
    }

    void add(String name, String[] tags) {
        providers.add(new ServiceProvider(name, tags));
    }

    ServiceProvider provider(String id) {
        for (ServiceProvider provider : providers) {
            if (provider.id.equals(id))
                return provider;
        }

        return null;
    }

    @Override
    public String toString() {
        return "Service [providers=" + providers + "]";
    }
}

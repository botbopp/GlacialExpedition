package glacialExpedition.repositories;

import glacialExpedition.models.explorers.Explorer;

import java.util.*;

public class ExplorerRepository implements Repository<Explorer> {

    private Map<String, Explorer> explorers;

    public ExplorerRepository() {
        explorers = new LinkedHashMap<>();
    }

    @Override
    public Collection<Explorer> getCollection() {
        return Collections.unmodifiableCollection(explorers.values());
    }

    @Override
    public void add(Explorer explorer) {
        explorers.put(explorer.getName(), explorer);
    }

    @Override
    public boolean remove(Explorer explorer) {
        return explorers.remove(explorer.getName()) != null;
    }

    @Override
    public Explorer byName(String name) {
        return explorers.get(name);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

            explorers.values().forEach(explorer -> sb.append(explorer).append(System.lineSeparator()));

            return sb.toString().trim();
    }
}



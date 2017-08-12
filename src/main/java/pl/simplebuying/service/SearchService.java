package pl.simplebuying.service;

import java.util.List;

import pl.simplebuying.model.Item;

public interface SearchService {

	public List<Item> searchByItemName(String itemName);
}

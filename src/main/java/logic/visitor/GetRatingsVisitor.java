package logic.visitor;

import logic.domain.Library;
import logic.domain.Rating;
import logic.util.JsonUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GetRatingsVisitor implements Visitor{

    @Override
    public String visit(Library library) {
        Map<String, Rating> map = findAuthorsRatings(library);
        List<Rating> authorsRatingList = convertMapToList(map);
        return JsonUtils.toJsonString(authorsRatingList);
    }

    private Map<String, Rating> findAuthorsRatings(Library library) {
        Map<String, Rating> map = new HashMap<>();
        library.books().forEach(book -> {
            if (book.getRating() != null && book.getAuthors() != null) {
                for (String author : book.getAuthors()) {
                    if (!map.containsKey(author)) {
                        map.put(author, new Rating(book.getRating(), author));
                    } else {
                        map.get(author).updateRatings(book.getRating());
                    }
                }
            }
        });
        return map;
    }

    private List<Rating> convertMapToList (Map<String, Rating> map) {
        return map.entrySet()
                .stream()
                .sorted(Comparator.comparingDouble(e -> -1 * e.getValue().getAverageRating()))
                .peek(entry -> entry.getValue().eraseUnnecessaryValues())
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}

package logic.visitor;

import logic.domain.Library;

public interface Visitor {

    String visit(Library library);

}

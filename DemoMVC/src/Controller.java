
public class Controller {

    private final Model model;

    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    public static void updateViewToMatchModel(Model model, View view) {
        String top = model.top();
        String bottom = model.bottom();
        view.updateTop(top);
        view.updateBottom(bottom);
    }

    public void processEnter(String top) {
        this.model.setBottom(this.model.bottom() + top);
        this.model.setTop("");
        updateViewToMatchModel(this.model, this.view);
    }

    public void processClear() {
        this.model.setBottom("");
        this.model.setTop("");
        updateViewToMatchModel(this.model, this.view);
    }
}

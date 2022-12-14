import components.stack.Stack;

/**
 * Controller class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        String output = "";
        if (model.output().length() > 0) {
            Stack<String> temp = model.output().newInstance();
            while (model.output().length() > 0) {
                String x = model.output().pop();
                temp.push(x);
            }
            while (temp.length() > 0) {
                String y = temp.pop();
                output += y;
                model.output().push(y);
            }
        }
        /*
         * Update view to reflect changes in model
         */
        view.updateInputDisplay(input);
        view.updateOutputDisplay(output);
        view.updateUndoAllowed(model.output().length() > 0);
    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        this.model.setInput("");
        while (this.model.output().length() > 0) {
            this.model.output().pop();
        }
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAppendEvent(String input) {
        this.model.setInput("");
        this.model.output().push(input);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processUndoEvent() {
        String undo = this.model.output().pop();
        this.model.setInput(undo);
        updateViewToMatchModel(this.model, this.view);
    }

}

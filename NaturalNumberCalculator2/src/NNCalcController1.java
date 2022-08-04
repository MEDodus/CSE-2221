import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Michael Dodus
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {
        // Update view topPanel to top ov (object value).
        view.updateTopDisplay(model.top());
        // Update view bottomPanel to bottom ov.
        view.updateBottomDisplay(model.bottom());
        /*
         * Update whether the subtract widget can be used. If top is greater
         * than bottom it can.
         */
        view.updateSubtractAllowed(model.top().compareTo(model.bottom()) >= 0);
        /*
         * Update whether the power widget can be used. If the bottom ov is
         * greater than or equal to zero and less than INT_LIMIT it can be used.
         * However, the program is not bulletproof on this operation listed in
         * the project description. Stack overflow will occur with large power
         * input numbers.
         */
        view.updatePowerAllowed(
                model.bottom().compareTo(new NaturalNumber2()) >= 0
                        && model.bottom().compareTo(INT_LIMIT) <= 0);
        /*
         * Update whether division widget can be used. So long as the bottom is
         * not zero it may.
         */
        view.updateDivideAllowed(!model.bottom().isZero());
        /*
         * Update whether the root widget can be used. So long as we are taking
         * the r-th root greater than or equal to two and less than or equal to
         * INT_LIMIT.
         */
        view.updateRootAllowed(model.bottom().compareTo(TWO) >= 0
                && model.bottom().compareTo(INT_LIMIT) <= 0);

    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {
        /*
         * Could use copyFrom, however, this simply clears the top and adds the
         * bottom to the top. May be more efficient.
         */
        this.model.top().clear();
        this.model.top().add(this.model.bottom());
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
        /*
         * Adds the top to the bottom and clears the top.
         */
        this.model.bottom().add(this.model.top());
        this.model.top().clear();
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        /*
         * Subtracts the bottom from the top and then clears the top and sets
         * the bottom to the top.
         */
        this.model.top().subtract(this.model.bottom());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {
        /*
         * Multiplies the two together and clears the top.
         */
        this.model.bottom().multiply(this.model.top());
        this.model.top().clear();
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processDivideEvent() {
        /*
         * Divides and reports the remainder in the top and the result in the
         * bottom.
         */
        NaturalNumber remainder = this.model.top().divide(this.model.bottom());
        this.model.bottom().transferFrom(this.model.top());
        this.model.top().transferFrom(remainder);
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processPowerEvent() {
        /*
         * Top ^ bottom-th power. Bottom is then set to the top and top is
         * cleared.
         */
        this.model.top().power(this.model.bottom().toInt());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    /*
     * Top to the r-th root. Bottom is then set to the top and top is cleared.
     */
    public void processRootEvent() {
        this.model.top().root(this.model.bottom().toInt());
        this.model.bottom().transferFrom(this.model.top());
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    /*
     * Multiplies the NN by 10 and adds the digits to be added to the zero at
     * the end.
     */
    public void processAddNewDigitEvent(int digit) {
        this.model.bottom().multiplyBy10(digit);
        updateViewToMatchModel(this.model, this.view);

    }

}

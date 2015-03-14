package pigpen.cascading;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.operation.OperationCall;
import cascading.tuple.Fields;
import clojure.lang.IFn;

public class PigPenFunction extends BaseOperation implements Function {

    private static final IFn PREPARE = OperationUtil.getVar("prepare");
    private static final IFn OPERATE = OperationUtil.getVar("function-operate");

    private final String context;

    public PigPenFunction(final String context, final Fields fields) {
        super(fields);
        this.context = context;
    }

    @Override
    public void prepare(final FlowProcess flowProcess, final OperationCall operationCall) {
        super.prepare(flowProcess, operationCall);
        operationCall.setContext(PREPARE.invoke(this.context));
    }

    @Override
    public void operate(final FlowProcess flowProcess, final FunctionCall functionCall) {
        OPERATE.invoke(functionCall);
    }
}
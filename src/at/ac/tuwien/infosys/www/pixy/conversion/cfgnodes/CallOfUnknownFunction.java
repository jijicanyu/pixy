package at.ac.tuwien.infosys.www.pixy.conversion.cfgnodes;

import at.ac.tuwien.infosys.www.phpparser.ParseNode;
import at.ac.tuwien.infosys.www.pixy.conversion.TacActualParam;
import at.ac.tuwien.infosys.www.pixy.conversion.TacPlace;
import at.ac.tuwien.infosys.www.pixy.conversion.Variable;

import java.util.LinkedList;
import java.util.List;

/**
 * A function call.
 *
 * @author Nenad Jovanovic <enji@seclab.tuwien.ac.at>
 */
public class CallOfUnknownFunction extends AbstractCfgNode {
    // name of the called unknown function
    private String functionName;

    // parameter list
    private List<TacActualParam> paramList;

    // temporary variable to hold the return value
    private Variable tempVar;

    // is this a call to an unknown method?
    private boolean isMethod;

// CONSTRUCTORS ********************************************************************

    public CallOfUnknownFunction(String functionName, List<TacActualParam> paramList,
                                 TacPlace tempPlace, ParseNode node, boolean isMethod) {

        super(node);
        this.functionName = functionName.toLowerCase();
        this.paramList = paramList;
        this.tempVar = (Variable) tempPlace;
        this.isMethod = isMethod;
    }

// GET *****************************************************************************

    public String getFunctionName() {
        return this.functionName;
    }

    public List<TacActualParam> getParamList() {
        return this.paramList;
    }

    public Variable getTempVar() {
        return this.tempVar;
    }

    public List<Variable> getVariables() {
        List<Variable> retMe = new LinkedList<>();
        for (TacActualParam param : this.paramList) {
            TacPlace paramPlace = param.getPlace();
            if (paramPlace instanceof Variable) {
                retMe.add((Variable) paramPlace);
            } else {
                retMe.add(null);
            }
        }
        return retMe;
    }

    public boolean isMethod() {
        return this.isMethod;
    }

// SET *****************************************************************************

    public void replaceVariable(int index, Variable replacement) {
        TacActualParam param = this.paramList.get(index);
        param.setPlace(replacement);
    }
}
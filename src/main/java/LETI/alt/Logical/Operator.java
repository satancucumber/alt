package LETI.alt.Logical;

public class Operator {
    private String name;
    private String description;
    private String formula;

    public Operator() {
    }

    public Operator(String name, String description, String formula) {
        this.name = name;
        this.description = description;
        this.formula = formula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }
    public static boolean isAlpha(String s)
    {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    public String getDescription(String... formulas) {
        String out = this.description;
        if (this.name.equals("!")) {
            out = out.replaceFirst("\\*", formulas[0]);
            return out;
        } else  {
            out = out.replaceFirst("\\*", formulas[0]);
            out = out.replaceFirst("\\*", formulas[1]);
            return out;
        }
    }

    public String toString(String... formulas) {
        String out = this.formula;
        if (this.name.equals("!")) {
            out = out.replaceFirst("\\*", formulas[0]);
            return out;
        } else  {
            out = out.replaceFirst("\\*", formulas[0]);
            out = out.replaceFirst("\\*", formulas[1]);
            return out;
        }
    }
}

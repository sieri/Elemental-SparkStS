package elementalSpark.stances;

import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.stances.AbstractStance;

public abstract class ElementAbstractStance extends AbstractStance {

    public ElementAbstractStance()
    {
        super();
        this.img = ImageMaster.loadImage("elementalSparkResources/images/char/SparkCharacter/orb/layer3.png");
    }

}

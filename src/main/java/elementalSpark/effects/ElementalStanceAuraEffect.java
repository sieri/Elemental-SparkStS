package elementalSpark.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import elementalSpark.stances.ElementAbstractStance.ElementType;

public class ElementalStanceAuraEffect extends StanceAuraEffect {

    public  final  ElementType elementType;

    public ElementalStanceAuraEffect(ElementType elementType) {
        super(null);
        this.elementType = elementType;


        this.color = new Color(MathUtils.random(0.7F, 0.1F), MathUtils.random(0.1F, 0.5F), MathUtils.random(0.0F, 0.1F), 0.0F);


    }
}

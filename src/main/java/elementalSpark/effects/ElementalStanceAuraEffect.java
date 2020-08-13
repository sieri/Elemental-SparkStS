package elementalSpark.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import elementalSpark.ElementalSpark;
import elementalSpark.stances.ElementAbstractStance.ElementType;

public class ElementalStanceAuraEffect extends StanceAuraEffect {

    public  final  ElementType elementType;

    public ElementalStanceAuraEffect(ElementType elementType) {
        super("");
        this.elementType = elementType;
        this.renderBehind = false;


        switch(elementType)
        {
            case Fire:
                this.color = new Color(MathUtils.random(0.7F, 1F), MathUtils.random(0.1F, 0.5F), MathUtils.random(0.0F, 0.1F), 0.0F);
                break;
            case Water:
                this.color = new Color(MathUtils.random(0.0F, 0.1F), MathUtils.random(0.1F, 0.2F), MathUtils.random(0.7F, 1.0F), 0.0F);
                break;
        
            case Air:
                this.color = new Color(MathUtils.random(0.4F, 0.5F), MathUtils.random(0.4F, 0.5F), MathUtils.random(0.0F, 0.1F), 0.0F);
                break;
        
            case Earth:
                this.color = new Color(MathUtils.random(0.0F, 0.1f), MathUtils.random(0.7F, 1F), MathUtils.random(0.0F, 0.1F), 0.0F);
                break;
        }
    }
}

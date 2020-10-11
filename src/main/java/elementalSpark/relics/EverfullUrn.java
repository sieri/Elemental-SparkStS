package elementalSpark.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ChangeStateAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.powers.BurnedPower;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makeRelicOutlinePath;
import static elementalSpark.ElementalSpark.makeRelicPath;

public class EverfullUrn extends CustomRelic {

    // ID, images, text.
    public static final String ID = ElementalSpark.makeID("EverfullUrn");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("EverfullUrn.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("EverfullUrn.png"));

    public EverfullUrn() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public void atTurnStart() {

        addToTop(new ChangeElementalStanceAction(ElementAbstractStance.ElementType.Water));
        flash();

    }
}

package elementalSpark.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.stances.ElementAirStance;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makePowerPath;

public class DodgePower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = ElementalSpark.makeID("DodgePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("DodgePower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("DodgePower32.png"));


    public DodgePower(int amount) {


        name = NAME;
        ID = POWER_ID;

        this.amount = 5;

        this.owner = AbstractDungeon.player;

        type = PowerType.BUFF;
        isTurnBased = false;


        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }


    @Override
    public void updateDescription() {

        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if(AbstractDungeon.player.stance instanceof ElementAirStance)
        {
            int randomNumber = new Random().random(100);

            if(randomNumber <= amount)
            {
                this.flash();
                return 0;
            }
        }
        return damageAmount;
    }

    @Override
    public AbstractPower makeCopy() {
        return new DodgePower(amount);
    }
}

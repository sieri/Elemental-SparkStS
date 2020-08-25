package elementalSpark.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.UpgradeRandomCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.util.TextureLoader;

import static elementalSpark.ElementalSpark.makePowerPath;

public class BurnedPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = ElementalSpark.makeID("BurnedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("BurnedPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("BurnedPower32.png"));

    public static final float DAMAGE_MOD_PER_STACK = 0.1f;

    public BurnedPower(int amount, AbstractCreature owner) {


        name = NAME;
        ID = POWER_ID;

        this.owner = owner;

        this.amount = amount;

        type = PowerType.DEBUFF;
        isTurnBased = false;


        // We load those textures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public BurnedPower(int amount){
        this(amount, AbstractDungeon.player);
    }



    @Override
    public void updateDescription() {

        description = DESCRIPTIONS[0] + String.format("%.0f", calculateDamageFactor()*100) + DESCRIPTIONS[1];

    }

    @Override
    public AbstractPower makeCopy() {
        return new BurnedPower(amount, owner);
    }

    private float calculateDamageFactor()
    {
        return amount*DAMAGE_MOD_PER_STACK;
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {

        //check if this is an attack
        if (type == DamageInfo.DamageType.NORMAL) {
            return damage + damage*calculateDamageFactor();
        } else {
            return damage;
        }
    }
}

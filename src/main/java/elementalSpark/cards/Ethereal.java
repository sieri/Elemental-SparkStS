package elementalSpark.cards;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.characters.TheSpark;
import elementalSpark.modifiers.ElementLimiterModifier;
import elementalSpark.powers.DodgePower;
import elementalSpark.powers.EtherealPower;
import elementalSpark.powers.StopAirPower;
import elementalSpark.powers.StopFirePower;
import elementalSpark.stances.ElementAbstractStance;

import static elementalSpark.ElementalSpark.makeCardPath;


public class Ethereal extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(Ethereal.class.getSimpleName());
    public static final String IMG = makeCardPath("Ethereal.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;


    private static final int COST = 3;
    private static final int UPGRADED_COST = 3;

    private static final int MAGIC = 5;
    private static final int UPGRADED_MAGIC = 3;

    // /STAT DECLARATION/


    public Ethereal() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = MAGIC;

        CardModifierManager.addModifier(this, new ElementLimiterModifier(ElementAbstractStance.ElementType.Air));

    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new EtherealPower())
        );

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new DodgePower(magicNumber))
        );


        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StopAirPower(4))
        );

        AbstractDungeon.actionManager.addToBottom(
                new ChangeElementalStanceAction(ElementAbstractStance.ElementType.None)
        );



    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(UPGRADED_MAGIC);
            initializeDescription();
        }
    }
}

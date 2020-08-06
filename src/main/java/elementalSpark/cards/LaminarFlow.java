package elementalSpark.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.SortDrawPile;
import elementalSpark.characters.TheSpark;
import elementalSpark.modifiers.ElementLimiterModifier;
import elementalSpark.powers.StopWaterPower;
import elementalSpark.stances.ElementAbstractStance;

import static elementalSpark.ElementalSpark.makeCardPath;


public class LaminarFlow extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(LaminarFlow.class.getSimpleName());
    public static final String IMG = makeCardPath("LaminarFlow.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;


    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    // /STAT DECLARATION/


    public LaminarFlow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        CardModifierManager.addModifier(this, new ElementLimiterModifier(ElementAbstractStance.ElementType.Water));
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new EmptyDeckShuffleAction()
        );

        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(p, p, new StopWaterPower(4))
        );

        AbstractDungeon.actionManager.addToBottom(
                new SortDrawPile()
        );

    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}

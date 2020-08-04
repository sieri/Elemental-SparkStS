package elementalSpark.cards;

import basemod.BaseMod;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AutoplayField;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.ConditionalDrawAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.characters.TheSpark;
import elementalSpark.stances.ElementAbstractStance;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static elementalSpark.ElementalSpark.makeCardPath;


public class SuddenWind extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(SuddenWind.class.getSimpleName());
    public static final String IMG = makeCardPath("SuddenWind.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;



    private static final int COST = -2;
    private static final int UPGRADED_COST = -2;

    // /STAT DECLARATION/


    public SuddenWind() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        AutoplayField.autoplay.set(this, true);

        magicNumber = baseMagicNumber = 1;
    }




    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(
                new ChangeElementalStanceAction(ElementAbstractStance.ElementType.Air)
        );

        AbstractDungeon.actionManager.addToBottom(
                new GainEnergyAction(magicNumber)
        );

    }


    // Upgraded stats.
    @Override
    public void upgrade() {

        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            upgradeMagicNumber(1);
            rawDescription = languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }


}

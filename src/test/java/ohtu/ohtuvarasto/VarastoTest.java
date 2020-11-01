package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoKelvottomanVarastonTilavuudella() {
        Varasto varasto2 = new Varasto(0);
        assertEquals(0.0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoKelvottomanVarastonTilavuudellaJaSaldolla() {
        Varasto varasto2 = new Varasto(0,0);
        assertEquals(0.0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisellaSaldollaLuodunVarastonSaldoAlussaOikein() {
        Varasto varasto2 = new Varasto(0,-2);
        assertEquals(0.0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void josSaldoaEnemmanKuinTilavuuttaSaldoAlussaOikein() {
        Varasto varasto2 = new Varasto(5,10);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonOikeinAnnetuillaParametreilla() {
        Varasto varasto2 = new Varasto(5, 2.5);
        assertEquals(5, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(2.5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoonEiLisataJosMaaraNegatiivinen() {
        double saldoAlussa = varasto.getSaldo();
        
        varasto.lisaaVarastoon(-5);
        assertEquals(saldoAlussa, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void josLisataanEnemmanKuinTilaaYlimaarainenHukkaan() {
        varasto.lisaaVarastoon(15);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttoEiMuutaSaldoa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-5);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void josMaaraSuurempiKuinSaldoVarastoTyhjaLopuksi() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void josMaaraSuurempiKuinSaldoPalautetaanSaldo() {
        varasto.lisaaVarastoon(5);
        double saldo = varasto.getSaldo();
        double saatuMaara = varasto.otaVarastosta(10);
        assertEquals(saatuMaara, saldo, vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoEsitysOikein() {
        varasto.lisaaVarastoon(5);
        String s = "saldo = " + varasto.getSaldo() + ", vielä tilaa " + varasto.paljonkoMahtuu();
        assertEquals(s, varasto.toString());
    }

}
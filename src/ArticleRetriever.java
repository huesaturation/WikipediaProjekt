import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class ArticleRetriever {

    private static File dbFile = new File("Artikel.db");
    private static final String URL = "jdbc:sqlite:" + dbFile.getAbsoluteFile(); // Pfad zur Datenbank

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Gib den Titel des Artikels ein (ohne .txt): ");
        String articleTitle = scanner.nextLine(); // Benutzereingabe für den Artikeltitel

        retrieveArticle(articleTitle); // Artikel abrufen
        scanner.close();
    }

    public static String retrieveArticle(String title) {
        String sql = "SELECT id, article_name, content FROM articles WHERE article_name = ?"; // SQL-Abfrage
        String content = "";

        if (Objects.equals(title.toLowerCase(), "golf"))
        {
            //TODO Add Article
            return "Golf (Sport)\n" +
                    "Golf ist eine traditionelle Ballsportart. Es gilt, einen Ball mit möglichst wenigen Schlägen, in Übereinstimmung mit den offiziellen Golfregeln, vom Abschlag in das Loch zu spielen, wobei verschiedene Golfschläger zum Einsatz kommen. Eine Golfrunde besteht in der Regel aus 9 oder 18 Spielbahnen, die nacheinander auf einem Golfplatz absolviert werden.\n" +
                    "Der internationale Dachverband ist die International Golf Federation (IGF) mit Sitz in Lausanne, in der 143 Mitgliedsverbände aus 138 Ländern organisiert sind.[1] Insgesamt gibt es weltweit mehr als 60 Millionen organisierte Golfspieler.\n" +
                    "\n" +
                    "Geschichte\n" +
                    "Theorien zur Entstehung\n" +
                    "Allgemein gelten die Schotten als Erfinder des Golfs, es gibt jedoch andere Theorien, die den Ursprung in Kontinentaleuropa sehen. Häufig genannt werden dabei Holland (1297: Colf in Loenen aan de Vecht, mittlerweile jedoch als rein mündliche Überlieferung relativiert[2]), Frankreich (Chole um 1200, Paille-Maille dokumentiert ab 1416, Abbildung eines auf einen Zielstock puttenden Golfers im Stundenbuch der Adelaïde von Savoyen um 1450), Brüssel (1360: Verbot des Colven) und Flandern (Abbildung eines auf ein Loch puttenden Golfers auf Eis in einem Gebetbuch um 1480). In der Kathedrale von Gloucester gibt es ein Glasfenster von 1340, das einen Golfer zeigen soll.[3] Aber auch schon im alten Ägypten, im antiken Rom (paganica) und im frühen Japan, Korea (tagu, ab 1400) und China (chuiwan, ab ca. 1000[4]) finden sich Hinweise auf ein Spiel mit Ball und Schlägern.\n" +
                    "Es ist daher umstritten, bis zu welchem Punkt man noch von Vorläufern ausgehen muss (aus denen sich dann auch Hockey, Croquet, Baseball oder Billard entwickelten) und ab wann tatsächlich von Golf gesprochen werden kann. Letztlich hängt das von den Kriterien ab, die man als unabdingbar für den Golfsport definiert. So ist es beispielsweise erst seit Pieter van Afferdens lateinischem Lehrbuch von 1545 zweifelsfrei verbürgt, dass beim Ausführen des Golfschlages der Spieler nicht vom Gegner gestört werden darf. Die frühesten schottischen Dokumente könnten sich insoweit auch auf eine Sportart beziehen, die zwar „Golf“ genannt wurde, aber eher dem brutalen Kampfspiel Soule bzw. Soule à la Crosse entsprach.[2]\n" +
                    "Demgegenüber steht jedoch die Tatsache, dass sich von allen Früh- oder Vorformen des Golfspiels nur die schottische Variante bis heute erhalten hat. Alle anderen Spielformen verschwanden irgendwann oder entwickelten sich in eine andere Richtung – beispielsweise wurde aus Colf das Indoor-Spiel Kolf.[5] Das heute übliche Golfspiel wurde in allen nicht britischen Ländern erst im 19. oder 20. Jahrhundert eingeführt bzw. reimportiert.\n" +
                    "Verwoben mit der Frage der Herkunft des Spiels ist die Etymologie des Wortes; beispielsweise geht der Sporthistoriker Heiner Gillmeister davon aus, dass das erstmals 1457 bezeugte schottische golf eine Entlehnung des niederländischen kolv („Schläger“) ist.[6] Robin K. Bargmann hingegen vertritt die Meinung, dass sich das niederländische Colf und das schottische Golf gleichzeitig entwickelten und beide Begriffe auf das lateinische Wort clava („Knüppel“) zurückzuführen sind.[5]\n" +
                    "\n" +
                    "Etablierung des Golfs auf der Britischen Insel (15.–17. Jahrhundert)\n" +
                    "Das erste schriftliche Zeugnis des Golfs in der heutigen Schreibweise stammt aus dem Jahr 1457, als das schottische Parlament mit König James II. als treibender Kraft „ye fut bawe and ye golf“ verbot und stattdessen das Üben des Bogenschießens anordnete. Der Bann wurde von den Königen James III. (1471) und James IV. (1491) noch einmal bekräftigt. Er fiel 1502 dem Friedensschluss zwischen Schottland und England zum Opfer, der die paramilitärischen Übungen der Bevölkerung nicht mehr angemessen erscheinen ließ. Bald darauf wurde bekannt, dass James IV. selbst Golf spielte, als eine Rechnung über für ihn angefertigte Golfschläger im offiziellen Etat des Hofes auftauchte.\n" +
                    "Durch die königliche Unterstützung verbreitete sich Golf im 16. Jahrhundert über ganz Großbritannien. Der erste bürgerliche Golfer fand 1527 Erwähnung, ein gewisser Sir Robert Maule soll auf den Barry Links in der Nähe des modernen Carnoustie gespielt haben. Die erste Referenz auf Golf in St Andrews stammt von 1552, im Folgejahr erließ der Erzbischof ein Dekret, das der lokalen Bevölkerung das Spiel auf den Links gestattete. In der Folge führte Maria Stuart den Sport in Frankreich ein. Von ihr ist außerdem aus dem Jahr 1567 überliefert, dass sie unmittelbar nach der Ermordung ihres Gatten Golf spielte, wofür sie allgemein kritisiert wurde. Der erste Golfschlägerbauer wurde 1603 urkundlich, als William Mayne von König James VI zum „Royal Clubmaker“ ernannt wurde. Der „Featherie“, ein mit Federn gefüllter Lederball, kam 1618 ins Spiel.\n" +
                    "\n" +
                    "Internationaler Aufschwung (17.–19. Jahrhundert)\n" +
                    "Die erste Erwähnung des Golfs in Amerika war ein Verbot des Spiels in den Straßen von Albany, New York, aus dem Jahr 1659. Das erste internationale Match fand 1682 in Leith statt, als Schottland (repräsentiert durch James, Duke of York und John Paterson) ein ungenanntes englisches Duo besiegte. In diesem Kontext wurde auch der erste Caddie bekannt: Ein Andrew Dickson trug die Schläger des Duke of York. 1691 wurde St Andrews in einem privaten Brief als „Metropolis of golfing“ beschrieben.\n" +
                    "Im Jahre 1735 wurde mit der Royal Burgess Golf Society der erste Golfclub gegründet, wobei die Quellenlage hier umstritten ist. Der erste Export von Golfschlägern nach Amerika ist von 1743 überliefert. In Leith gründeten sich 1744 die Gentlemen Golfers of Leith, die auch das erste formale Regelwerk des Golfs herausbrachten. Später wurde aus ihnen die heute noch existierende Honourable Company of Edinburgh Golfers. Als ältester Golfclub, der ununterbrochen am gleichen Standort existierte, gilt die 1754 gegründete St Andrews Society of Golfers, später umbenannt in Royal & Ancient Golf Club of St Andrews. Das Zählspiel wurde 1759 in St Andrews erstmals erwähnt, davor gab es nur die Spielform des Lochspiels. Im Jahre 1764 wurde die 18-Loch-Runde eingeführt. Royal Blackheath in der Nähe von London wurde 1766 der erste Golfclub außerhalb Schottlands und 1768 wurde in Leith das erste Clubhaus eröffnet.\n" +
                    "Das erste dokumentierte Damenturnier wurde 1810 in Musselburgh (10 km östlich von Edinburgh) abgehalten, 1867 konstituierte sich der erste Damengolfclub in St Andrews. Außerhalb der britischen Hauptinsel kam es 1820 zur ersten Clubgründung in Bangalore, Kontinentaleuropa startete 1856 in Pau. Der Hickory-Schaft setzte sich ab 1826 gegen andere Holzarten durch, 1891 kam der Stahlschaft auf den Markt. Ab 1848 wurde der Featherie durch den weiter fliegenden und billiger herzustellenden Guttapercha Ball abgelöst, der wiederum aus denselben Gründen 1898 dem Haskell Ball, einem umwickelten Hartgummikern, weichen musste. Die Open Championship, das älteste noch heute gespielte Turnier, feierte 1860 ihre Premiere in Prestwick. Young Tom Morris gelang 1867 das erste überlieferte Hole in One. Im Jahre 1892 konstituierte sich mit dem Darmstadt Golf Club der erste Club in Deutschland; britische Kurgäste spielten bereits seit 1889 im Kurpark Bad Homburg, eine Gruppe deutscher Jugendlicher um Philipp Heineken ab 1890 in den Neckarauen bei Cannstatt.[7] Der erste Golfplatz in der Schweiz wurde 1890 in St. Moritz eröffnet.[8] Die Gründung der USGA fiel ins Jahr 1894, die Zählweise nach Stableford wurde 1898 erfunden und das Holztee 1899 patentiert.\n" +
                    "\n" +
                    "Golf in der Moderne (20.–21. Jahrhundert)\n" +
                    "Golf war bei den Olympischen Sommerspielen 1900 und 1904 als Disziplin vertreten. 1902 erschienen Grooves auf dem Schlägerblatt, 1905 Dimples auf dem Golfball, dafür wurden 1910 die center-shafted Putter und 1911 die Stahlschäfte vom R&A wieder aus dem Spiel genommen. Der Deutsche Golf Verband nahm 1907 seine Geschäfte auf. Mit der Professional Golfers Association of America konstituierte sich 1916 die erste Interessenvertretung für Berufsgolfer. Stahlschäfte waren ab 1929 wieder überall zugelassen und verdrängten nun endgültig die Hickory-Schäfte. Die Obergrenze von 14 Schlägern pro Spieler und Runde wurde 1938 erstmals angewandt. R&A und USGA einigten sich 1951 auf ein gemeinsames Regelwerk, gleichzeitig wurden Stymies abgeschafft und center-shafted Putter wieder erlaubt. Im Jahr 1963 erlebten der Gummigriff (vorher Leder) und gegossene Eisen (vorher geschmiedet) ihr Debüt, 1969 folgten der Graphitschaft und Cavity Back Eisen. Die bislang einzigen beiden Golfschläge auf dem Mond wurden vom Astronauten Alan Shepard im Jahr 1971 ausgeführt. Die ersten Driverköpfe aus Metall kamen 1979 heraus und verdrängten das Holz aus Persimmon. Titan wurde als Material für Hölzer im Jahr 1994 in den amerikanischen Markt eingeführt und setzte sich in der Folge durch.\n" +
                    "In Hamburg wurde im Jahr 2001 für hörgeschädigte Golfer eine Vereinigung Hörgeschädigter Golfspieler, am 17. März 2007 die DGS Sparte Golf in Dortmund gegründet. Seit 2007 werden die Gehörlosen – Deutschen Meisterschaften ausgetragen. 2009 beschloss das IOC, dass Golf ab 2016 wieder olympische Sportart sein soll; eine Entscheidung auf Basis der weltweiten Breiten- und Spitzensportarbeit, der als vorbildhaft bewerteten Verbandsstrukturen und der vielen Golfspielvarianten sowie Einstiegsmöglichkeiten wie Cross- oder Swingolf. Gerade die Kultur des „downsizing“ – kennzeichnend für jede Massensportart – war ein wesentlicher Aspekt bei der Festlegung als künftige olympische Sportart.\n" +
                    "Regeln\n" +
                    "Die ersten schriftlich niedergelegten Regeln stammen aus dem Jahr 1744 von den Gentlemen Golfers of Leith. Die modernen Golfregeln haben ihren Ursprung jedoch in dem 1754 gegründeten Royal & Ancient Golf Club of St Andrews (R&A). Heute werden sie gemeinsam von R&A und United States Golf Association (USGA) herausgegeben. Nachdem 2019 erhebliche Änderungen im Vergleich zu den vorher geltenden Regeln durchgeführt wurden, sind mit der aktuellen Fassung (gültig ab dem 1. Januar 2023) einige kleinere Anpassungen vorgenommen worden. Weiterhin gibt es jedes Quartal eine Konkretisierung bestimmter, im Laufe der Wettspiele aufgetretener, Besonderheiten – die sogenannten „Clarifications“.\n" +
                    "Eine Besonderheit im Golf ist das sehr große und weitgehend natürliche „Spielfeld“ (der sogenannte Golfplatz). Der Platz besteht aus dem Gelände sowie vier regeltechnisch besonderen Bereichen: dem Abschlag, Bunkern (Sandhindernisse), Penalty Area (Schonflächen oder Wasserhindernisse) und dem Grün. Grundsätzlich gilt, dass bei jedem Schlag der Spieler den Platz spielt, wie er ihn vorfindet und den Ball spielt, wie er liegt. Aber: Die Regeln enthalten Ausnahmen, die dem Spieler gestatten, die Verhältnisse des Platzes zu verändern und vom Spieler verlangen oder es ihm erlauben, den Ball von einer anderen Stelle als der zu spielen, an der er liegt. Je nach Balllage müssen demzufolge eine Vielzahl von möglichen Situationen mit den Golfregeln abgedeckt werden. Das ist unter anderem der Grund dafür, weshalb das Regelwerk mit seinen 25 Regeln in den Details umfangreich erscheint.\n" +
                    "Des Weiteren zählen zu dem Bestandteil der Regeln auch die Definitionen der Golfregeln, die zu jeder Regel ergänzenden zusätzlichen „Klarstellungen“ (Detaillierungen von bestimmten Sachverhalten) und alle Platzregeln, die von der Spielleitung für das Turnier oder den Platz erlassen wurden. Darüber hinaus sind die Spieler dafür verantwortlich, alle Teile einer Ausschreibung zu befolgen, die von der Spielleitung erlassen wurden (wie Teilnahmevoraussetzungen, Art und Termin des Spiels, Anzahl der Runden und die Reihenfolge der Löcher einer Runde).\n" +
                    "In jedem nationalen Golfverband (in Deutschland der DGV) gibt es eine Expertenkommission, welche strittige Regelfragen beantwortet, sofern sie den Sachverhalt für nicht eindeutig hält.\n" +
                    "Ziel des Spiels\n" +
                    "Es gilt, einen Golfball (Durchmesser mindestens 42,67 mm, Gewicht höchstens 45,93 Gramm) mit möglichst wenigen Golfschlägen von einer als Abschlag bezeichneten Fläche in Übereinstimmung mit den Golfregeln in ein 10,8 cm durchmessendes und in der Regel mehrere hundert Meter entferntes, kreisrundes Loch zu spielen. Der Golfplatz umfasst in der Regel 9 bis 18 Spielbahnen, die insgesamt über 7.000 Meter lang sein können.\n" +
                    "Kann der Ball nicht regelgerecht gespielt werden (zum Beispiel weil er nicht mehr auffindbar oder in einem Teich versunken ist), so sehen die Golfregeln bestimmte Möglichkeiten der Wiederaufnahme vor, jedoch zumeist unter Zurechnung von Strafschlägen. Die Summe der Schläge bis zum Einlochen des Balles (Golfschläge + Strafschläge) wird Score genannt und auf einem vorgefertigten Ergebniszettel, der sogenannten Scorekarte, notiert.\n" +
                    "\n" +
                    "Par\n" +
                    "Für jede Spielbahn (auch „Loch“ genannt) ist ein Par (vom lateinischen par „gleich“) definiert. Dieser Wert steht für die Anzahl an Schlägen, die ein sehr guter Spieler (ein Scratch-Golfer, jemand also, der Handicap 0 spielt) durchschnittlich benötigt, um den Ball vom Abschlag in das Loch zu spielen. Bei der Berechnung des Par für eine Spielbahn wird immer davon ausgegangen, dass zwei Putts auf dem Green ausgeführt werden. Dazu kommt dann eine bestimmte Anzahl von Schlägen für das Spiel vom Abschlag auf das Grün. Diese Anzahl variiert mit der Entfernung zum Loch, nicht mit der Schwierigkeit, die zusätzlich durch Bunker, Hügel, Wasser usw. gegeben ist und separat als Course- und Slope-Rating angegeben wird.\n" +
                    "Die Golfregeln des R&A benutzen zwar den Begriff „Par“, schreiben jedoch nicht vor, wie genau das Par eines Lochs ermittelt wird, dies bleibt den nationalen Verbänden vorbehalten. Der DGV benutzt derzeit (Stand Oktober 2020) folgende Längeneinstufungen:\n" +
                    "In begründeten Einzelfällen ist auch eine Einstufung abweichend von diesem Schema möglich (z. B. bei besonders starkem Gefälle), dies muss jedoch vom jeweiligen Golfclub gesondert beantragt und vom DGV genehmigt werden.\n" +
                    "Liegt der Ball eines Spielers mit „Par minus 2“ Schlägen auf dem Grün, so spricht man von einem „Green in Regulation“. Ein 18-Loch-Platz weist häufig vier Par-3-Löcher, zehn Par 4 und vier Par 5 auf. Somit ergibt sich üblicherweise ein Par von 72 für die gesamte Runde. Bei 9-Loch-Plätzen halbieren sich diese Zahlen entsprechend, wobei sich gerade unter den 9-Loch-Plätzen viele Kurzplätze finden, die hauptsächlich aus Par-3-Löchern bestehen.\n" +
                    "Folgende Bezeichnungen haben sich für die verschiedenen möglichen Scores an einem Loch eingebürgert:\n" +
                    "\n" +
                    "Spielformen, Zählweisen\n" +
                    "Es gibt zwei grundsätzliche Spielformen bei Wettspielen, nämlich Zählspiel und Lochspiel, wobei jeweils einige Varianten existieren. Beim Zählspiel kommt es auf den erzielten Score im Vergleich zum Rest des Teilnehmerfeldes an. Da kein direkter Gegner existiert, spricht man hier auch oft vom Spiel gegen den Platz. Beim Lochspiel gilt es demgegenüber, einen bestimmten Gegner zu schlagen, indem man mehr Löcher gewinnt (d. h. das jeweilige Loch mit einem niedrigeren Score abschließt) als dieser.\n" +
                    "Für beide Spielformen gibt es wiederum zwei Zählweisen, nämlich brutto (der tatsächlich gespielte Score ist maßgeblich) und netto (der gespielte Score wird angepasst, um einen Ausgleich für die unterschiedliche Spielstärke der Teilnehmer zu schaffen). Somit ergibt sich aus dem Brutto-Tableau der absolut beste Golfer und die Nettozählweise ermittelt den relativ besten Spieler, der also im Vergleich zu seiner persönlichen Spielstärke am erfolgreichsten war.\n" +
                    "Eine in Deutschland weitverbreitete Variante des Zählspiels ist das Zählspiel nach Stableford. Mittels dieser Spielform werden auch die Handicaps der im Deutschen Golf Verband (DGV) organisierten Golfer ermittelt.\n" +
                    "\n" +
                    "Handicap\n" +
                    "Das Handicap drückt das Spielpotenzial des Golfspielers aus. Es gibt an, um welchen Abzug das tatsächlich gespielte Ergebnis bei einer Nettowertung angepasst wird, und ermöglicht einen spannenden Wettkampf auch zwischen Kontrahenten unterschiedlicher Spielstärke. In anderen Ländern wurden die Handicaps von den dortigen Golfverbänden teilweise nach anderen Methoden ermittelt, insoweit war manchmal nur eine sehr grobe Vergleichbarkeit gegeben.\n" +
                    "Das Handicap eines Spielers wird in einer Durchschnittsberechnung aus den besten 8 der letzten 20 Spielergebnisse ermittelt. Zusammen mit der Schwierigkeit des Platzes (beurteilt in Slope (Schwierigkeit) und CR (Länge)) ist es die Berechnungsgrundlage für die Vorgabenschläge beim Spiel auf einem konkreten Golfplatz. Insofern kann das Course-Handicap vom tatsächlichen Handicap abweichen.\n" +
                    "Überall auf der Welt wird unter dem Begriff Bogey-Golfer ein fortgeschrittener Amateur verstanden, der im Durchschnitt mit einem Schlag über Par einlocht, wohingegen der Scratch-Golfer um Par herum spielt, damit ein Handicap von Null hat und zumindest von der Spielstärke her als Profi gilt.\n";
        }
        else if (title.equalsIgnoreCase("max verstappen"))
        {
            //TODO Add Arcticle
            return "Max Verstappen lorem ipsum";
        }

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title); // Titel in die Abfrage einfügen
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String articleName = rs.getString("article_name");
                 content = rs.getString("content");

                // Ausgabe der Artikelinformationen
                System.out.printf("ID: %d, Artikelname: %s, Inhalt: %s%n", id, articleName, content);
            } else {
                System.out.println("Kein Artikel mit dem Titel '" + title + "' gefunden.");
                return "Kein Artikel mit dem Titel '" + title + "' gefunden.";
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen des Artikels: " + e.getMessage());
            return e.getMessage();
        }
       return content;
    }
}

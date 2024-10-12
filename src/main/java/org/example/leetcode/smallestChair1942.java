package org.example.leetcode;

import org.junit.Test;

import java.util.*;

public class smallestChair1942 {
    class Solution {
        public int smallestChair(int[][] times, int targetFriend) {

            int[][] sortedTimes = Arrays.stream(times)
                    .sorted((o1, o2) -> o1[0] - o2[0])
                    .toArray(int[][]::new);
            int targetArrival = times[targetFriend][0];
            TreeSet<Integer> chairSet = new TreeSet<>();
            // key: chairNum value:leave time
            Map<Integer, Integer> leavingMap = new HashMap<>();
            TreeSet<Integer> leavedChair = new TreeSet<>();
            for (int i = 0; i < sortedTimes.length; i++) {
                int arrivalTime = sortedTimes[i][0];

                // in time arrivalTime, if someone leaves, clear the chair first
                Iterator<Map.Entry<Integer, Integer>> iterator = leavingMap.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<Integer, Integer> entry = iterator.next();
                    Integer value = entry.getValue();
                    if (value <= arrivalTime) {
                        int chair = entry.getKey();
                        leavedChair.add(chair);
                        chairSet.remove(chair);
                        iterator.remove();
                    }
                }

                if (chairSet.size() == 0 && leavedChair.size() == 0) {
                    chairSet.add(0);
                    leavingMap.put(0, sortedTimes[i][1]);
                    // if encounter the target,get from map
                    if (arrivalTime == targetArrival) {
                        return 0;
                    }
                    continue;
                }
                // someone leaves so use it anyway
                int chair;
                if (leavedChair.size() != 0) {
                    chair = leavedChair.first();
                    leavedChair.remove(chair);
                    // if encounter the target,get from map
                } else {
                    chair = chairSet.last() + 1;
                    // if encounter the target,get from map
                }
                chairSet.add(chair);
                leavingMap.put(chair, sortedTimes[i][1]);
                if (arrivalTime == targetArrival) {
                    return chair;
                }
            }
            return 0;
        }
    }
    @Test
    public void test(){
        Solution solution = new Solution();
//        int[][] times = {
//                {16605,59052},{57541,74338},{95777,95949},{87720,97778},{61090,88277},{48986,78317},{36382,87353},{32069,67350},{76245,89505},{71551,97226},{3726,79172},{46216,54966},{54956,94399},{76660,92281},{91869,99716},{90368,94272},{65517,70435},{30841,75044},{59584,97779},{2834,22978},{34355,98143},{55722,85350},{43700,86056},{50143,68674},{25490,34772},{63487,75745},{34667,67197},{53092,91214},{77390,88035},{98254,98879},{93566,100000},{69365,84876},{56184,61150},{886,30944},{13565,24772},{81932,98865},{98375,99485},{88488,97366},{11390,59478},{1868,14067},{47757,93733},{45475,65385},{35637,95964},{28362,93380},{13167,59636},{42802,87761},{19417,94743},{61648,79659},{77244,78041},{91860,95519},{97011,97711},{67316,74939},{66433,91021},{52846,69557},{4125,83652},{30630,75482},{90359,99898},{82788,96577},{64557,81134},{30808,57124},{67883,97097},{87629,99801},{26824,32651},{92775,99694},{86452,95680},{66260,67586},{99770,99875},{59797,70431},{55808,65622},{72350,85581},{32977,57299},{89549,91314},{79811,99906},{74376,91955},{8395,29881},{21601,62820},{18216,64716},{51413,64033},{2087,64456},{77311,90007},{49382,66988},{54066,86753},{98360,99337},{38459,38676},{68638,97236},{98457,98588},{19933,96015},{18483,73078},{28104,47599},{94640,97870},{53640,94636},{78666,84000},{65962,94690},{18081,77540},{93076,98481},{81960,88559},{80383,99572},{6571,71874},{13861,55325},{76343,99538},{6413,72673},{87054,94803},{8751,50849},{36681,56866},{33607,84485},{37071,54770},{46093,80982},{4678,6290},{51406,91606},{7707,18326},{9763,63361},{77954,84017},{8780,25719},{98036,99216},{96555,98028},{89569,98297},{65891,99912},{61215,87312},{66576,76372},{67500,91614},{44632,84486},{19712,27009},{12157,68475},{78236,83122},{29387,89624},{37187,90690},{19113,81549},{68870,91269},{71287,71980},{22992,73203},{58813,79454},{94977,95810},{50272,78932},{25715,91131},{13353,67659},{38330,67609},{67231,77929},{95431,99471},{68873,79081},{53668,54806},{42610,88486},{73985,96472},{63646,70617},{71546,81044},{39805,98605},{24136,87838},{96709,97999},{78576,85298},{30777,90249},{44350,77366},{42866,91658},{50068,90434},{21185,91697},{12321,22371},{93976,95565},{72704,73139},{44684,69526},{729,4652},{26667,78483},{37881,72258},{23837,35231},{79360,81231},{52796,76434},{3729,20614},{93267,95123},{48173,87783},{63527,74873},{46414,68344},{30283,47084},{6661,67109},{46070,90615},{28314,79103},{96632,97367},{2723,87201},{33505,35691},{71397,84088},{72444,76214},{9100,50311},{42406,79728},{24113,28704},{11611,22111},{28389,65348},{53084,94884},{9075,63294},{27110,64269},{40131,75472},{21882,77505},{73974,94760},{32909,40964},{82391,92139},{91686,99392},{40537,65352},{45853,80262},{14064,74867},{4449,45145},{27186,53224},{41751,54400},{83202,90703},{56955,90538},{4922,36338},{21606,90852},{91715,93705},{22190,53611},{28457,29400},{36197,85784},{13709,41587},{26768,34954},{70190,98500},{91040,98870},{93612,98797},{878,68911},{12246,26150},{83270,98615},{79856,90234},{35916,76024},{71278,83379},{77332,93412},{3664,10066},{3989,39152},{16273,92671},{24468,60949},{78749,88684},{64936,70890},{41536,54240},{40642,66700},{31041,83984},{3886,33936},{5653,61492},{62035,72854},{42264,76234},{70211,74731},{55033,99894},{76560,82361},{45174,62561},{41706,47569},{96318,97600},{13697,63103},{93205,97370},{91309,93436},{88570,94739},{21869,96501},{48346,85406},{92644,92924},{81344,83633},{42143,57933},{68082,72091},{63972,71966},{66790,68432},{30618,45784},{70525,96439},{93697,97540},{77395,78796},{54351,65307},{32634,65025},{88829,89678},{51565,51588},{88424,90891},{58033,83425},{10211,90701},{28133,69732},{4972,86248},{57851,61886},{73469,85913},{16295,17978},{38133,90168},{93190,95053},{65697,67242},{8732,89221},{2812,43869},{9308,21286},{5284,92464},{7966,53910},{10533,72882},{1897,96523},{88098,99531},{7443,41248},{91353,97276},{20229,52136},{91768,92990},{68897,84121},{34741,46648},{47492,87036},{51849,72454},{66579,77323},{19024,78105},{30066,98470},{94368,96506},{39863,55185},{53900,65337},{65262,94197},{28261,37097},{25034,59413},{21662,44709},{77793,98399},{76268,95066},{98466,99355},{39558,90275},{87656,96793},{83897,96995},{25253,53026},{96284,98478},{27781,97731},{19095,85672},{55753,59029},{80287,88708},{87620,98248},{57142,99085},{11446,14472},{3681,66652},{84277,92200},{69067,73517},{84095,84849},{91494,95067},{54680,64114},{46760,80407},{15396,64574},{42759,64203},{91256,96042},{84461,92751},{66689,76689},{72833,96447},{42867,87462},{78593,88817},{68572,81974},{14058,49153},{7708,96850},{29636,85135},{36997,92512},{34843,44387},{66265,89518},{9883,16392},{26343,40143},{10143,54738},{58618,96608},{80960,95924},{3057,55676},{20247,20337},{47024,88237},{43606,97246},{75657,84464},{28570,38852},{11687,70056},{42728,54787},{89939,92763},{81950,99471},{402,97574},{38387,88449},{16384,67064},{84052,89882},{2712,78849},{56499,68178},{37754,82769},{63146,78276},{17549,78842},{88202,96578},{83124,85249},{77022,81040},{73645,87365},{91032,99863},{71276,88741},{63285,87964},{47826,90883},{56489,91583},{15477,74655},{19171,87695},{96912,99331},{59009,93774},{3786,26489},{8605,72581},{52838,91042},{91086,94075},{18548,30602},{79850,99742},{94375,99123},{18499,20332},{55402,74391},{36615,79441},{24126,25836},{16603,96383},{35509,63028},{88600,92576},{56850,69046},{84866,99967},{49541,86911},{16756,57576},{40251,68228},{55967,66290},{64336,97382},{20466,34246},{95771,98473},{27422,34278},{9803,77787},{6512,49725},{95882,99511},{69849,99324},{95685,98286},{59551,87747},{57335,83273},{34804,44007},{39360,64224},{97773,99111},{62797,93228},{53647,88724},{88404,91127},{98502,98555},{66356,86435},{64838,86602},{56164,66817},{64534,83802},{6891,46793},{13883,84129},{9560,18022},{60545,81206},{22985,33142},{91845,94209},{31947,88379},{3349,13710},{6907,52852},{51529,84537},{14189,25466},{45441,90950},{30522,67503},{45799,67009},{28658,43802},{57093,71251},{34155,88937},{4987,42046},{67150,98234},{10586,81725},{75439,95111},{95877,96382},{71895,97579},{35672,61874},{66972,71805},{3355,82318},{50656,90192},{50295,64299},{22588,87695},{26338,49336},{92535,94299},{11816,91880},{40165,84567},{11246,60465},{90479,94841},{16035,50464},{8829,34585},{60808,84886},{57610,79360},{2499,53157},{69905,77083},{27926,76002},{49145,52521},{65634,98468},{73102,75724},{96240,98996},{70093,80422},{24214,34896},{46944,86561},{48156,91439},{80451,94631},{22759,24448},{36341,85553},{37089,81212},{38072,54000},{10655,91474},{19527,23806},{14036,53865},{32043,82664},{10559,55325},{37019,53705},{52859,98160},{39211,43883},{43365,54620},{31309,57249},{31829,36641},{52463,75764},{25618,93914},{34562,40066},{50237,92467},{17840,55905},{23732,53743},{80674,99586},{17992,62572},{73943,87606},{46148,72487},{33959,72621},{39820,91283},{1843,24532},{74825,97864},{2072,27315},{76309,95370},{45144,70226},{98022,99399},{88237,99738},{64696,86223},{79747,90686},{25293,38032},{54651,74078},{44225,54380},{97325,99121},{48886,96539},{52403,72350},{94419,98934},{42076,69897},{89006,94561},{82779,86976},{76498,81976},{86625,89842},{90446,96455},{29769,89797},{91712,98454},{16860,84381},{2507,58106},{69092,83340},{61217,89242},{18739,97638},{1173,6786},{55292,83708},{52529,96837},{99408,99530},{82734,93855},{55417,83332},{63325,90608},{44149,46387},{81860,97657},{2944,38318},{85965,92665},{31351,82619},{83874,97367},{65408,91924},{84576,84621},{26039,78210},{34733,89430},{57433,71276},{89783,96911},{30071,54281},{56413,89752},{10311,77149},{61367,75434},{27252,89430},{94141,94235},{71769,96188},{8703,79405},{31007,75793},{74289,80676},{31436,75270},{26352,27985},{47342,72573},{6107,21721},{50162,65344},{12470,48494},{91661,95626},{66889,76889},{76829,91601},{60022,96455},{40788,77982},{96683,96876},{72753,94908},{51567,84656},{9099,32370},{27507,27901},{62030,90320},{74873,95493},{38097,62333},{40304,83835},{35472,87517},{55908,65347},{9160,55032},{470,6099},{93375,99641},{38406,41855},{52494,58551},{43183,84868},{32906,75691},{38616,40522},{82586,84894},{89244,95169},{14227,21684},{96600,99923},{35763,60291},{58366,87094},{36189,77971},{21052,26630},{43854,66430},{75498,86456},{18694,35018},{28505,34178},{2367,43346},{53974,89624},{83711,97116},{31416,46313},{5373,12179},{70142,91963},{9578,71685},{73378,95839},{84354,99598},{4546,62915},{58264,60952},{30915,34327},{74387,83063},{8044,94080},{34064,35898},{37807,64834},{22663,47530},{99803,99999},{12592,93082},{53386,98091},{60385,73309},{17962,62515},{29167,44748},{40088,85073},{55159,71553},{48697,53071},{66917,68345},{78478,78693},{95193,97936},{67758,96649},{42973,80880},{4382,87138},{77325,79490},{96313,97682},{76458,97875},{55925,59594},{27922,83513}
//        };
        int[][] times = {
                {4, 5}, {12, 13}, {5, 6}, {1, 2}, {8, 9}, {9, 10}, {6, 7},
                {3, 4}, {7, 8}, {13, 14}, {15, 16}, {14, 15}, {10, 11},
                {11, 12}, {2, 3}, {16, 17}
        };
        int targetFriend = 15;
//        int[][] times = {
//                {33889, 98676}, {80071, 89737}, {44118, 52565}, {52992, 84310},
//                {78492, 88209}, {21695, 67063}, {84622, 95452}, {98048, 98856},
//                {98411, 99433}, {55333, 56548}, {65375, 88566}, {55011, 62821},
//                {48548, 48656}, {87396, 94825}, {55273, 81868}, {75629, 91467}
//        };
        int chair = solution.smallestChair(times, targetFriend);
        System.out.println(chair);
    }
}

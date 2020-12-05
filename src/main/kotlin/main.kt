const val DISCOUNT_STANDARD = 100.0   // скидка, руб. при предыдущих покупках на сумму от VALUE_STANDARD
const val DISCOUNT_ADDITIONAL = .05   // скидка, % при предыдущих покупках на сумму от VALUE_ADDITIONAL
const val DISCOUNT_MELOMAN = .01      // скидка при количестве покупок от MELOMAN_SALES_COUNT и более
const val MELOMAN_SALES_COUNT = 3     // будем считать, что меломан - это 3+ покупки
const val VALUE_STANDARD = 1001.0
const val VALUE_ADDITIONAL = 10_001.0

fun main() {
    var salesCount = 0
    var salesValue = 0.0

    while(true) {
        print("Введите сумму покупки, целое число, руб. (пустая строка - выход): ")
        val input = readLine()
        if(input!!.isEmpty()) {
            println("Работа завершена.")
            return
        }

        val saleCurrent = input.toDouble()
        val isAdditionalDiscount = salesValue >= VALUE_ADDITIONAL
        val isStandardDiscount = salesValue >= VALUE_STANDARD
        val saleLessOrEqualsThenDiscount = isStandardDiscount && (saleCurrent <= DISCOUNT_STANDARD)

        val saleCurrentWithDiscount =
            // повышенная скидка, или стандартная при покупке меньше фиксированной скидки
            if (isAdditionalDiscount || saleLessOrEqualsThenDiscount)
                saleCurrent * (1.0 - DISCOUNT_ADDITIONAL)
            // стандартная скидка
            else if (isStandardDiscount)
                saleCurrent - DISCOUNT_STANDARD
            // без скидки
            else saleCurrent

        val melomanDiscount: Double =
            // скидка меломана
            if (salesCount >= MELOMAN_SALES_COUNT) DISCOUNT_MELOMAN
            else 0.0

        val saleWithSummaryDiscount = saleCurrentWithDiscount * (1.0 - melomanDiscount)
        println("Текущая покупка: $saleCurrent,\n" +
                "    - со скидкой: $saleCurrentWithDiscount,\n" +
                "    - итого, со скидкой \"меломана\": $saleWithSummaryDiscount")

        salesCount++
        salesValue += saleWithSummaryDiscount
    }
}
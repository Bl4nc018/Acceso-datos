## Ejercicio 10:

def dental_insurance_cost(company_name):
    if company_name == "Ratoncito Pérez S.L.":
        return "40"
    else:
        return 30


## Ejercicio 11:

def show_annual_cost(a_company):
    cost = dental_insurance_cost(a_company)
    annual_cost = int(cost) * 12
    print("Si contratas la compañía " + a_company + ", el coste anual será " + str(annual_cost) + " euros")


if __name__ == '__main__':
    company = "DentaPlus S.L."
    cost = dental_insurance_cost(company)
    print("Tu seguro dental con la compañía " + company + " cuesta " + str(cost) + " euros al mes")

 ## Ejercicio 11:

    show_annual_cost("Dientes Baratos S.L.")
    show_annual_cost("Ratoncito Pérez S.L.")

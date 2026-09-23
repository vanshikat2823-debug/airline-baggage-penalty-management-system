/* =========================================================
   API URLs
========================================================= */

const dashboardApiUrl = "/api/dashboard/summary";

const airlineApiUrl = "/api/airlines";


/* =========================================================
   DOM ELEMENTS
========================================================= */

const refreshButton =
    document.getElementById("refreshButton");


const refreshAirlinesButton =
    document.getElementById("refreshAirlinesButton");


const airlineForm =
    document.getElementById("airlineForm");


const cancelEditButton =
    document.getElementById("cancelEditButton");


const airlineTableBody =
    document.getElementById("airlineTableBody");


const airlineMessage =
    document.getElementById("airlineMessage");


const airlineFormTitle =
    document.getElementById("airlineFormTitle");


const saveAirlineButton =
    document.getElementById("saveAirlineButton");


const airlineNameInput =
    document.getElementById("airlineName");


const allowedWeightInput =
    document.getElementById("allowedWeight");


const penaltyPerKgInput =
    document.getElementById("penaltyPerKg");


/* =========================================================
   EDIT STATE
========================================================= */

let editingAirlineId = null;


/* =========================================================
   LOAD DASHBOARD
========================================================= */

async function loadDashboardData() {

    const messageElement =
        document.getElementById("message");


    try {

        messageElement.textContent =
            "Loading dashboard data...";


        const response =
            await fetch(dashboardApiUrl);


        if (!response.ok) {

            throw new Error(
                "Failed to load dashboard data."
            );

        }


        const data =
            await response.json();


        /* -------------------------
           Update Summary Cards
        ------------------------- */

        document.getElementById(
            "totalPassengers"
        ).textContent =
            data.totalPassengers;


        document.getElementById(
            "totalBaggage"
        ).textContent =
            data.totalBaggage;


        document.getElementById(
            "exceededBaggage"
        ).textContent =
            data.exceededBaggage;


        document.getElementById(
            "withinLimitBaggage"
        ).textContent =
            data.withinLimitBaggage;


        document.getElementById(
            "totalPenaltyAmount"
        ).textContent =
            formatCurrency(
                data.totalPenaltyAmount
            );


        /* -------------------------
           Update Status
        ------------------------- */

        document.getElementById(
            "statusExceeded"
        ).textContent =
            data.exceededBaggage;


        document.getElementById(
            "statusWithinLimit"
        ).textContent =
            data.withinLimitBaggage;


        messageElement.textContent =
            "Dashboard data loaded successfully.";


    } catch (error) {

        console.error(
            "Dashboard Error:",
            error
        );


        messageElement.textContent =
            "Unable to load dashboard data.";

    }

}


/* =========================================================
   LOAD AIRLINES
========================================================= */

async function loadAirlines() {

    showAirlineMessage(
        "Loading airlines..."
    );


    try {

        const response =
            await fetch(airlineApiUrl);


        if (!response.ok) {

            throw new Error(
                "Failed to load airlines."
            );

        }


        const airlines =
            await response.json();


        displayAirlines(airlines);


        showAirlineMessage(
            `${airlines.length} airline(s) loaded successfully.`
        );


    } catch (error) {

        console.error(
            "Airline Error:",
            error
        );


        showAirlineMessage(
            "Unable to load airlines.",
            true
        );

    }

}


/* =========================================================
   DISPLAY AIRLINES IN TABLE
========================================================= */

function displayAirlines(airlines) {

    airlineTableBody.innerHTML = "";


    if (airlines.length === 0) {

        const row =
            document.createElement("tr");


        row.innerHTML = `
            <td colspan="5" class="empty-row">
                No airlines found.
            </td>
        `;


        airlineTableBody.appendChild(row);

        return;
    }


    airlines.forEach(airline => {

        const row =
            document.createElement("tr");


        row.innerHTML = `
            <td>${airline.id}</td>

            <td>${escapeHtml(airline.name)}</td>

            <td>${airline.allowedWeight} kg</td>

            <td>${formatCurrency(airline.penaltyPerKg)}</td>

            <td>

                <button
                    class="edit-button"
                    onclick="editAirline(${airline.id})">

                    Edit

                </button>


                <button
                    class="delete-button"
                    onclick="deleteAirline(${airline.id})">

                    Delete

                </button>

            </td>
        `;


        airlineTableBody.appendChild(row);

    });

}


/* =========================================================
   ADD / UPDATE AIRLINE
========================================================= */

airlineForm.addEventListener(
    "submit",
    async function (event) {

        event.preventDefault();


        const airlineData = {

            name: airlineNameInput.value.trim(),

            allowedWeight:
                Number(
                    allowedWeightInput.value
                ),

            penaltyPerKg:
                Number(
                    penaltyPerKgInput.value
                )

        };


        if (
            !airlineData.name ||
            airlineData.allowedWeight <= 0 ||
            airlineData.penaltyPerKg <= 0
        ) {

            showAirlineMessage(
                "Please enter valid airline details.",
                true
            );

            return;
        }


        try {

            let response;


            /* =========================
               UPDATE
            ========================= */

            if (editingAirlineId !== null) {

                response =
                    await fetch(
                        `${airlineApiUrl}/${editingAirlineId}`,
                        {
                            method: "PUT",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify(
                                    airlineData
                                )
                        }
                    );

            }

            /* =========================
               CREATE
            ========================= */

            else {

                response =
                    await fetch(
                        airlineApiUrl,
                        {
                            method: "POST",

                            headers: {
                                "Content-Type":
                                    "application/json"
                            },

                            body:
                                JSON.stringify(
                                    airlineData
                                )
                        }
                    );

            }


            if (!response.ok) {

                const errorData =
                    await response.text();


                throw new Error(
                    errorData ||
                    "Failed to save airline."
                );

            }


            if (editingAirlineId !== null) {

                showAirlineMessage(
                    "Airline updated successfully."
                );

            } else {

                showAirlineMessage(
                    "Airline added successfully."
                );

            }


            resetAirlineForm();


            await loadAirlines();


            /*
             * Refresh dashboard because
             * airline data may affect
             * future baggage calculations.
             */

            await loadDashboardData();


        } catch (error) {

            console.error(
                "Save Airline Error:",
                error
            );


            showAirlineMessage(
                "Unable to save airline.",
                true
            );

        }

    }
);


/* =========================================================
   EDIT AIRLINE
========================================================= */

async function editAirline(id) {

    try {

        const response =
            await fetch(
                `${airlineApiUrl}/${id}`
            );


        if (!response.ok) {

            throw new Error(
                "Unable to fetch airline."
            );

        }


        const airline =
            await response.json();


        editingAirlineId =
            airline.id;


        airlineNameInput.value =
            airline.name;


        allowedWeightInput.value =
            airline.allowedWeight;


        penaltyPerKgInput.value =
            airline.penaltyPerKg;


        airlineFormTitle.textContent =
            "Edit Airline";


        saveAirlineButton.textContent =
            "Update Airline";


        cancelEditButton.classList.remove(
            "hidden"
        );


        airlineNameInput.focus();


        showAirlineMessage(
            `Editing airline: ${airline.name}`
        );


    } catch (error) {

        console.error(
            "Edit Airline Error:",
            error
        );


        showAirlineMessage(
            "Unable to load airline details.",
            true
        );

    }

}


/* =========================================================
   DELETE AIRLINE
========================================================= */

async function deleteAirline(id) {

    const confirmed =
        confirm(
            "Are you sure you want to delete this airline?"
        );


    if (!confirmed) {

        return;

    }


    try {

        const response =
            await fetch(
                `${airlineApiUrl}/${id}`,
                {
                    method: "DELETE"
                }
            );


        if (!response.ok) {

            const errorData =
                await response.text();


            throw new Error(
                errorData ||
                "Failed to delete airline."
            );

        }


        showAirlineMessage(
            "Airline deleted successfully."
        );


        await loadAirlines();


        await loadDashboardData();


    } catch (error) {

        console.error(
            "Delete Airline Error:",
            error
        );


        showAirlineMessage(
            "Unable to delete airline. It may be linked to existing passengers.",
            true
        );

    }

}


/* =========================================================
   CANCEL EDIT
========================================================= */

cancelEditButton.addEventListener(
    "click",
    function () {

        resetAirlineForm();

        showAirlineMessage(
            "Edit cancelled."
        );

    }
);


/* =========================================================
   RESET AIRLINE FORM
========================================================= */

function resetAirlineForm() {

    editingAirlineId = null;


    airlineForm.reset();


    airlineFormTitle.textContent =
        "Add New Airline";


    saveAirlineButton.textContent =
        "Add Airline";


    cancelEditButton.classList.add(
        "hidden"
    );

}


/* =========================================================
   REFRESH DASHBOARD
========================================================= */

refreshButton.addEventListener(
    "click",
    async function () {

        await loadDashboardData();

        await loadAirlines();

    }
);


/* =========================================================
   REFRESH AIRLINES
========================================================= */

refreshAirlinesButton.addEventListener(
    "click",
    loadAirlines
);


/* =========================================================
   CURRENCY FORMATTER
========================================================= */

function formatCurrency(amount) {

    return new Intl.NumberFormat(
        "en-IN",
        {
            style: "currency",

            currency: "INR",

            maximumFractionDigits: 2
        }
    ).format(amount);

}


/* =========================================================
   HTML ESCAPE
========================================================= */

function escapeHtml(value) {

    const div =
        document.createElement("div");


    div.textContent =
        value;


    return div.innerHTML;

}


/* =========================================================
   AIRLINE MESSAGE
========================================================= */

function showAirlineMessage(
    message,
    isError = false
) {

    airlineMessage.textContent =
        message;


    airlineMessage.style.color =
        isError
            ? "#dc2626"
            : "#16a34a";

}


/* =========================================================
   INITIAL LOAD
========================================================= */

loadDashboardData();

loadAirlines();
public enum ParsedInputType {
    None,                    //
    DontUnderstand,            //
    Greeting,                //
    Farewell,                //
    PleaseComeBack,            //

    SetDestination,            //
    CheckWeather,            //
    Activity,                //
    Accomodations,
    TravelMethod,            //
    Language,                //
    LocalTransport,
    GeneralInfo,
    City,                    //
    //Questions
    Actions,                    //
    Dates,                    //
    Budget,                    //
    BookingInfo,                //

    SimpleYes,                //
    SimpleNo,                //

    Debug_Reset,
    Debug_ShowStats;

    public boolean isWellFormed() {
        return (this != ParsedInputType.None) &&
               (this != ParsedInputType.DontUnderstand) &&
               (this != ParsedInputType.Debug_Reset) &&
               (this != ParsedInputType.Debug_ShowStats);
    }
}
public enum ParsedInputType {
    None,                   //
    TooLong,
    DontUnderstand,         //
    Greeting,               //
    Farewell,               //
    PleaseComeBack,         //
    Thanks,
    GetKeyword,
    SetDestination,         //
    CheckWeather,           //
    Distance,
    Activity,               //
    Accomodations,
    TravelMethod,           //
    GetAround,			//  
    Language,               //
    ListCities,                   //  For distances
    Dates,                  //   
    Budget,                 //  amount affordable for hotels
    
    SimpleYes,              //
    SimpleNo,               //

    Debug_Reset,
    Debug_ShowStats,
    Debug_Enable;

    public boolean isWellFormed() {
        return (this != ParsedInputType.None) &&
               (this != ParsedInputType.DontUnderstand) &&
               (this != ParsedInputType.TooLong) &&
               (this != ParsedInputType.Debug_Reset) &&
               (this != ParsedInputType.Debug_ShowStats);
    }
}
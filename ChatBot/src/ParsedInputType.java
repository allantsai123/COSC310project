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
    Food,					// Just added food case for more dialogue if possible.
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
<<<<<<< HEAD
    Debug_ShowStats;
    
=======
    Debug_ShowStats,
    Debug_Enable;
>>>>>>> 479be4433c6592d25d6bbbf7b01d36f42dffefa6

    public boolean isWellFormed() {
        return (this != ParsedInputType.None) &&
               (this != ParsedInputType.DontUnderstand) &&
               (this != ParsedInputType.TooLong) &&
               (this != ParsedInputType.Debug_Reset) &&
               (this != ParsedInputType.Debug_ShowStats);
    }
}
$("#calendar").evoCalendar({
  theme: "Royal Navy",
  language: "es",
  titleFormat: "MM yyyy",
  firstDayOfWeek: "1",
  calendarEvents: [
    {
      id: "bHay68s", // Event's ID (required)
      name: "Conferencia 1", // Event name (required)
      date: "April/1/2021", // Event date (required)
      type: "holiday", // Event type (required)
      everyYear: true, // Same event every year (optional)
    },
    {
      name: "Siembra de rosas",
      badge: "Plantas", // Event badge (optional)
      date: ["April/13/2021", "April/15/2021"], // Date range
      description: "Siembra de rosas en el arboreto Carmelo Palma", // Event description (optional)
      type: "event",
      color: "#a8a8a8", // Event custom color (optional)
      link: "https://google.com",
    },
  ],
});


export interface Schedule {
  scheduleId: number;
  description: string;
  startDate: string; // o Date si lo parseas
  endDate: string;
  dailyHours: number;
  enabled: boolean;
}

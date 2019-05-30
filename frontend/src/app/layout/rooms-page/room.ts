export class Room {
    roomName: string;
    location: string;
    roomCapacity: number;

    constructor(theName?: string, theLocation?: string, theCap?: number) {
        this.roomName = theName;
        this.location = theLocation;
        this.roomCapacity = theCap;
    }

}

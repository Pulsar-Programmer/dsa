public class Value {
    public String iso3, iso2, capital, currency, currency_name, currency_symbol, tld, lang, region, subregion, nationality, emoji, emojiU;
    public int numeric_code, phone_code, region_id, subregion_id;
    public float latitude, longitude;
    
    public Value(String iso3, String iso2, int numeric_code, int phone_code, 
            String capital, String currency, String currency_name,
            String currency_symbol, String tld, String lang, String region, int region_id, String subregion, int subregion_id,
            String nationality, 
            float latitude, float longitude, 
            String emoji, String emojiU) {
        
        this.iso3 = iso3;
        this.iso2 = iso2;
        this.capital = capital;
        this.currency = currency;
        this.currency_name = currency_name;
        this.currency_symbol = currency_symbol;
        this.tld = tld;
        this.lang = lang;
        this.region = region;
        this.subregion = subregion;
        this.emoji = emoji;
        this.emojiU = emojiU;
        this.numeric_code = numeric_code;
        this.phone_code = phone_code;
        this.region_id = region_id;
        this.subregion_id = subregion_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nationality = nationality;
    }

    
}

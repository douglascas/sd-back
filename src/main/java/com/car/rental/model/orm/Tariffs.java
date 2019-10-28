package com.car.rental.model.orm;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tariffs")
public class Tariffs implements Serializable, Comparable<Tariffs> {

    private static final long serialVersionUID = 1375379027697046495L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "automobile_id", nullable = false)
    private Automobile automobile;

    @Column(name = "Weekday", precision = 10, scale = 2, nullable = false)
    private BigDecimal weekday;

    @Column(name = "Weekend_day", precision = 10, scale = 2, nullable = false)
    private BigDecimal weekendDay;

    @Column(name = "Weekday_loyalty", precision = 10, scale = 2, nullable = false)
    private BigDecimal weekdayLoyalty;

    @Column(name = "Weekend_day_loyalty", precision = 10, scale = 2, nullable = false)
    private BigDecimal weekendDayLoyalty;

    @Column(name = "actived", nullable = false)
    private Boolean actived = Boolean.TRUE;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Automobile getAutomobile() {
        return automobile;
    }
    public void setAutomobile(Automobile automobile) {
        this.automobile = automobile;
    }
    public BigDecimal getWeekday() {
        return weekday;
    }
    public void setWeekday(BigDecimal weekday) {
        this.weekday = weekday;
    }
    public BigDecimal getWeekendDay() {
        return weekendDay;
    }
    public void setWeekendDay(BigDecimal weekendDay) {
        this.weekendDay = weekendDay;
    }
    public BigDecimal getWeekdayLoyalty() {
        return weekdayLoyalty;
    }
    public void setWeekdayLoyalty(BigDecimal weekdayLoyalty) {
        this.weekdayLoyalty = weekdayLoyalty;
    }
    public BigDecimal getWeekendDayLoyalty() {
        return weekendDayLoyalty;
    }
    public void setWeekendDayLoyalty(BigDecimal weekendDayLoyalty) {
        this.weekendDayLoyalty = weekendDayLoyalty;
    }
    public Boolean getActived() {
        return actived;
    }
    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    /**
     * Orders fares from lowest price to highest.
     * @param otherTariffs
     * @return
     */
    @Override
    public int compareTo(Tariffs otherTariffs) {
        if (this.weekday.compareTo(otherTariffs.getWeekday()) < 0 ) {
            return -1;
        } if (this.weekday.compareTo(otherTariffs.getWeekday()) > 0 ) {
            return 1;
        }
        return 0;
    }
}

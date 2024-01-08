package com.sparta.ticketauction.domain.admin.request;

import java.util.List;

import com.sparta.ticketauction.domain.goods_sequence_seat.entity.GoodsSequenceSeat;
import com.sparta.ticketauction.domain.goods_sequence_seat.entity.SellType;
import com.sparta.ticketauction.domain.seat.entity.Seat;
import com.sparta.ticketauction.domain.seat.request.SeatRequest;
import com.sparta.ticketauction.domain.sequence.entity.Sequence;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GoodsSequenceSeatRequest {
	@NotNull(message = "잘못된 공연 가격입니다")
	private final Long generalAuctionPrice;

	@NotNull(message = "잘못된 경매 가격입니다.")
	private final Long auctionPrice;

	@Valid
	@NotNull(message = "정확한 경매 좌석을 입력해 주세요.")
	private final List<SeatRequest> auctionSeats;

	public GoodsSequenceSeat generalToEntity(Seat seat, Sequence sequence, SellType sellType) {
		return GoodsSequenceSeat
			.builder()
			.price(this.generalAuctionPrice)
			.seat(seat)
			.sequence(sequence)
			.sellType(sellType)
			.isSelled(false)
			.build();
	}

	public GoodsSequenceSeat auctionToEntity(Seat seat, Sequence sequence, SellType sellType) {
		return GoodsSequenceSeat
			.builder()
			.price(this.auctionPrice)
			.seat(seat)
			.sequence(sequence)
			.sellType(sellType)
			.isSelled(false)
			.build();
	}
}
